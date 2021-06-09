package kr.hs.entrydsm.common.context.sender;

import java.util.Map;

public interface ContentSender {
    boolean sendMessage(String email, String templateName, Map<String, String> params);
}
