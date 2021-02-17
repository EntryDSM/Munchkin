package kr.hs.entrydsm.notification.usecase;

import kr.hs.entrydsm.notification.domain.entity.Notification;
import kr.hs.entrydsm.notification.domain.entity.Type;
import kr.hs.entrydsm.notification.integrate.admin.TeacherRepository;
import kr.hs.entrydsm.notification.domain.repository.NotificationRepository;
import kr.hs.entrydsm.notification.usecase.dto.MessagesResponse;
import kr.hs.entrydsm.notification.usecase.dto.NotificationMessageResponse;
import kr.hs.entrydsm.notification.usecase.dto.UpdateMessageRequest;
import kr.hs.entrydsm.notification.usecase.exception.TypeNotFoundException;
import kr.hs.entrydsm.notification.usecase.exception.UserNotAuthorizedException;
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
        Notification notification = notificationRepository.findByType(Type.valueOf(messageRequest.getType()))
                .orElseThrow(TypeNotFoundException::new);
        if(teacherRepository.isTeacher()){
            notification.update(messageRequest);
        }
        else {
            throw new UserNotAuthorizedException();
        }

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
