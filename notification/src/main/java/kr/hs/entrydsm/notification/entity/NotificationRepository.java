package kr.hs.entrydsm.notification.entity;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository {
    Optional<Notification> findByType(Type type);
    List<Notification> findAll();
}
