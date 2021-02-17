package kr.hs.entrydsm.notification.infrastructure.database;

import kr.hs.entrydsm.notification.domain.entity.Notification;
import kr.hs.entrydsm.notification.domain.entity.Type;
import kr.hs.entrydsm.notification.domain.repository.NotificationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepositoryManager extends JpaRepository<Notification, Type>, NotificationRepository {

    @Override
    Optional<Notification> findByType(Type type);

    @Override
    List<Notification> findAll();

}
