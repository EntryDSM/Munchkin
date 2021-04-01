package kr.hs.entrydsm.common.sms;

public interface MessageSender {
    boolean sendMessage(String phoneNumber, String content);
}
