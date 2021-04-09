package kr.hs.entrydsm.common.context.sms;

public interface MessageSender {
    boolean sendMessage(String phoneNumber, String content);
}
