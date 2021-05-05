package kr.hs.entrydsm.user.infrastructure.database;

import kr.hs.entrydsm.user.entity.User;
import kr.hs.entrydsm.user.entity.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryManager extends CrudRepository<User, Long>, UserRepository {
    @Query(value = "select u.* from tbl_user as u join tbl_status as s on u.receipt_code = s.receipt_code where u.receipt_code like ?1 and u.is_daejeon like ?2 and u.telephone_number like ?3 and u.name like ?4 and (u.application_type = 'COMMON' or ?5) and (u.application_type = 'MEISTER' or ?6) and (u.application_type = 'SOCIAL' or ?7) and s.is_printed_arrived like ?8 and s.is_paid like ?9", nativeQuery = true)
    Page<User> findAllByUserInfo(String receiptCode, String isDaejeon, String telephoneNumber, String name, boolean isCommon, boolean isMeister, boolean isSocial, boolean isPrintedArrived, boolean isPaid, Pageable pageable);

    @Override
    User save(User user);

    List<User> findAllBy();
    long countAllBy();
}
