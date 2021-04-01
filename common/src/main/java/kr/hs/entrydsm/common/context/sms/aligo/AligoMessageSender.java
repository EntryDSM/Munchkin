package kr.hs.entrydsm.common.context.sms.aligo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import kr.hs.entrydsm.common.context.sms.MessageSender;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class AligoMessageSender implements MessageSender {

    @Value("${sms.aligo.url}")
    private String aligoUrl;

    @Value("${sms.aligo.key}")
    private String aligoKey;

    @Value("${sms.aligo.user-id}")
    private String aligoUserId;

    @Value("${sms.aligo.sender}")
    private String aligoSender;

    @Override
    public boolean sendMessage(String phoneNumber, String content) throws IOException {
        Map<String, Object> urlParams = new HashMap<>();
        urlParams.put("key", aligoKey);
        urlParams.put("user_id", aligoUserId);
        urlParams.put("sender", aligoSender);
        urlParams.put("receiver", phoneNumber);
        urlParams.put("msg", content);

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(aligoUrl + "?" + getParamsString(urlParams))
                .method("POST", body)
                .build();

        Response response = client.newCall(request).execute();
        String jsonString = Objects.requireNonNull(response.body()).string();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        AligoResponseDto responseDto = objectMapper.readValue(jsonString, AligoResponseDto.class);

        return responseDto.getResultCode() == 1;
    }

    public static String getParamsString(Map<String, Object> params) {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            result.append("=");
            result.append(URLEncoder.encode((String) entry.getValue(), StandardCharsets.UTF_8));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }

}
