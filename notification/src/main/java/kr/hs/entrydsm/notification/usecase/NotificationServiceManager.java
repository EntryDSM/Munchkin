package kr.hs.entrydsm.notification.usecase;

import kr.hs.entrydsm.notification.domain.entity.Notification;
import kr.hs.entrydsm.notification.domain.entity.Type;
import kr.hs.entrydsm.notification.domain.integrate.admin.TeacherRepository;
import kr.hs.entrydsm.notification.domain.repository.NotificationRepository;
import kr.hs.entrydsm.notification.usecase.dto.UpdateMessageRequest;
import kr.hs.entrydsm.notification.usecase.exception.TypeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
