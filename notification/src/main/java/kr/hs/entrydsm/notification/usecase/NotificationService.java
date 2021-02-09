package kr.hs.entrydsm.notification.usecase;

import kr.hs.entrydsm.notification.usecase.dto.MessagesResponse;
import kr.hs.entrydsm.notification.usecase.dto.UpdateMessageRequest;

public interface NotificationService {
    void updateMessage(UpdateMessageRequest messageRequest);
    MessagesResponse getMessage();
}
