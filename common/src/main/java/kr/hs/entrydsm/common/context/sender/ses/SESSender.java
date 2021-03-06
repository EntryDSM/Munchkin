package kr.hs.entrydsm.common.context.sender.ses;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsync;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailRequest;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.hs.entrydsm.common.context.sender.ContentSender;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.Future;

@RequiredArgsConstructor
@Component
public class SESSender implements ContentSender {

    private static final String UTF_8_ENCODED_SOURCE_NAME = "=?utf-8?B?7J6F7ZWZ7KCE7ZiV7Iuc7Iqk7YWc?=";

    private final ObjectMapper objectMapper;
    private final AmazonSimpleEmailServiceAsync amazonSimpleEmailServiceAsync;

    @Override
    public boolean sendMessage(String email, String templateName, Map<String, String> params) {
        SendTemplatedEmailRequest request = new SendTemplatedEmailRequest()
                .withDestination(new Destination().withToAddresses(email))
                .withTemplate(templateName)
                .withSource(UTF_8_ENCODED_SOURCE_NAME + " <noreply@entrydsm.hs.kr>")
                .withTemplateData(paramToJson(params));

        Future<SendTemplatedEmailResult> result = amazonSimpleEmailServiceAsync.sendTemplatedEmailAsync(request);
        return result.isDone();
    }

    @SneakyThrows
    private String paramToJson(Map<String, String> params) {
        String data = objectMapper.writeValueAsString(params);
        data = data.replaceAll("\"", "\\\"");
        return data;
    }

}
