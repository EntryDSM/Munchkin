package kr.hs.entrydsm.notification.infrastructure.database;

import kr.hs.entrydsm.notification.entity.Notification;
import kr.hs.entrydsm.notification.entity.Type;
import kr.hs.entrydsm.notification.entity.NotificationRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepositoryManager extends CrudRepository<Notification, Type>, NotificationRepository {

    @Override
    Optional<Notification> findByType(Type type);

    @Override
    List<Notification> findAll();

}
