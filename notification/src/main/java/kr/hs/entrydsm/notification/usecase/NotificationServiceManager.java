package kr.hs.entrydsm.notification.usecase;

import kr.hs.entrydsm.notification.domain.entity.Notification;
import kr.hs.entrydsm.notification.domain.entity.Type;
import kr.hs.entrydsm.notification.integrate.admin.TeacherRepository;
import kr.hs.entrydsm.notification.domain.repository.NotificationRepository;
import kr.hs.entrydsm.notification.usecase.dto.MessagesResponse;
import kr.hs.entrydsm.notification.usecase.dto.NotificationMessageResponse;
import kr.hs.entrydsm.notification.usecase.dto.UpdateMessageRequest;
import kr.hs.entrydsm.notification.usecase.exception.TypeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NotificationServiceManager implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public void updateMessage(UpdateMessageRequest messageRequest) {
        //permission이 teacher라면 허용, 아니라면 오류
        Notification notification = notificationRepository.findByType(Type.valueOf(messageRequest.getType()))
                .orElseThrow(TypeNotFoundException::new);

        notification.update(messageRequest);
    }

    @Override
    public MessagesResponse getMessage() {
        //토큰 확인
        List<Notification> notifications = notificationRepository.findAll();
        List<NotificationMessageResponse> messageResponses = new ArrayList<>();

        for(Notification notification : notifications) {
            messageResponses.add(
              NotificationMessageResponse.builder()
                      .type(notification.getType())
                      .content(notification.getContent())
                      .build()
            );
        }

        return MessagesResponse.builder()
                .messageResponses(messageResponses)
                .build();
    }

}
