package kr.hs.entrydsm.application.usecase.image;

import com.amazonaws.auth.AWS4Signer;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import static kr.hs.entrydsm.application.usecase.image.AWS4SignerBase.*;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl extends AWS4Signer implements ImageService {

    private static final String SCHEME = "AWS4";

    private static final String ALGORITHM = "HMAC-SHA256";

    private static final Integer EXPIRES = 900;

    private final SimpleDateFormat dateTimeFormat = new SimpleDateFormat(ISO8601BasicFormat);
    private final SimpleDateFormat dateStampFormat = new SimpleDateFormat("yyyyMMdd");

    private final AmazonS3 s3;

    @Value("${aws.s3.accessKey}")
    private String accessKey;

    @Value("${aws.s3.secretKey}")
    private String secretKey;

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Value("${aws.region.static}")
    private String region;

    @Value("${aws.s3.base_image_url}")
    private String baseImageUrl;

    @Override
    public String upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String randomName = UUID.randomUUID().toString();
        String filename = randomName + "." + ext;

        s3.putObject(new PutObjectRequest(bucket, "images/" + filename, file.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.AuthenticatedRead));

        return filename;
    }

    @Override
    public void delete(String objectName) {
        s3.deleteObject(bucket, objectName);
    }

    @Override
    public String generateObjectUrl(String objectName) throws MalformedURLException {
        URL endpointUrl = new URL("https://" + baseImageUrl + "/" + objectName);

        // X-Amz-Algorithm
        String x_amz_algorithm = SCHEME + "-" + ALGORITHM;

        // X-Amz-Credential
        dateStampFormat.setTimeZone(new SimpleTimeZone(0, "UTC"));
        Date now = new Date();
        String dateStamp = dateStampFormat.format(now);
        String scope = dateStamp + "/" + region + "/s3/aws4_request";
        String x_amz_credential = accessKey + "/" + scope;

        // X-Amz-Date
        dateTimeFormat.setTimeZone(new SimpleTimeZone(0, "UTC"));
        String x_amz_date = dateTimeFormat.format(now);

        // X-Amz-SignedHeaders
        Map<String, String> headers = new HashMap<>();
        String hostHeader = endpointUrl.getHost();
        headers.put("Host", hostHeader);
        String canonicalizedHeaderNames = getCanonicalizeHeaderNames(headers);
        String canonicalizedHeaders = AWS4SignerBase.getCanonicalizedHeaderString(headers);

        // X-Amz_Signature
        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("X-Amz-Algorithm", x_amz_algorithm);
        queryParameters.put("X-Amz-Credential", x_amz_credential);
        queryParameters.put("X-Amz-Date", x_amz_date);
        queryParameters.put("X-Amz-SignedHeaders", canonicalizedHeaderNames);
        queryParameters.put("X-Amz-Expires", Integer.toString(EXPIRES));
        String canonicalizedQueryParameters = AWS4SignerBase.getCanonicalizedQueryString(queryParameters);

        String canonicalRequest = getCanonicalRequest(endpointUrl, "GET",
                canonicalizedQueryParameters, canonicalizedHeaderNames,
                canonicalizedHeaders, AWS4SignerBase.UNSIGNED_PAYLOAD);

        String stringToSign = getStringToSign(SCHEME, ALGORITHM, x_amz_date, scope, canonicalRequest);

        byte[] kSecret = (SCHEME + secretKey).getBytes();
        byte[] kDate = AWS4SignerBase.sign(dateStamp, kSecret, "HmacSHA256");
        byte[] kRegion = AWS4SignerBase.sign(region, kDate, "HmacSHA256");
        byte[] kService = AWS4SignerBase.sign("s3", kRegion, "HmacSHA256");
        byte[] kSigning = AWS4SignerBase.sign(TERMINATOR, kService, "HmacSHA256");
        byte[] signature = AWS4SignerBase.sign(stringToSign, kSigning, "HmacSHA256");

        StringBuilder authString = new StringBuilder();

        authString.append(endpointUrl.toString());
        authString.append("?X-Amz-Algorithm=" + queryParameters.get("X-Amz-Algorithm"));
        authString.append("&X-Amz-Credential=" + queryParameters.get("X-Amz-Credential"));
        authString.append("&X-Amz-Date=" + queryParameters.get("X-Amz-Date"));
        authString.append("&X-Amz-Expires=" + queryParameters.get("X-Amz-Expires"));
        authString.append("&X-Amz-SignedHeaders=" + queryParameters.get("X-Amz-SignedHeaders"));
        authString.append("&X-Amz-Signature=" + BinaryUtils.toHex(signature));

        return authString.toString();
    }

    public byte[] getObject(String fileName) throws IOException {
        S3Object object = s3.getObject(bucket, fileName);
        return IOUtils.toByteArray(object.getObjectContent());
    }
}
