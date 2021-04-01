package kr.hs.entrydsm.common.context.sms;

import org.springframework.stereotype.Component;

@Component
public class AligoMessageSender implements MessageSender {

    @Override
    public boolean sendMessage(String phoneNumber, String content) {
        return false;
    }

}
