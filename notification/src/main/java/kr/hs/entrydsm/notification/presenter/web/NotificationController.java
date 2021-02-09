package kr.hs.entrydsm.notification.presenter.web;

import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.notification.usecase.NotificationService;
import kr.hs.entrydsm.notification.usecase.dto.UpdateMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/notification")
@Published
@RestController
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public void updateMessage(UpdateMessageRequest messageRequest) {
        notificationService.updateMessage(messageRequest);
    }

}
