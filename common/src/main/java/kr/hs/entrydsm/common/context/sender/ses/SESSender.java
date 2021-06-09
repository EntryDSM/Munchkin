package kr.hs.entrydsm.common.context.sender.ses;

import kr.hs.entrydsm.common.context.sender.ContentSender;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SESSender implements ContentSender {

    @Override
    public boolean sendMessage(String email, String templateName, Map<String, String> params) {
        return false;
    }
}
