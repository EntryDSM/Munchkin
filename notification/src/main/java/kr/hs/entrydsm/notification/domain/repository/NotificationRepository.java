package kr.hs.entrydsm.notification.domain.repository;

import kr.hs.entrydsm.notification.domain.entity.Notification;
import kr.hs.entrydsm.notification.domain.entity.Type;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository {
    Optional<Notification> findByType(Type type);
    List<Notification> findAll();
}
