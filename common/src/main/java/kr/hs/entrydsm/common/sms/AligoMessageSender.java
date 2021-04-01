package kr.hs.entrydsm.common.sms;

import org.springframework.stereotype.Component;

@Component
public class AligoMessageSender implements MessageSender {

    @Override
    public boolean sendMessage(String phoneNumber, String content) {
        return false;
    }

}
