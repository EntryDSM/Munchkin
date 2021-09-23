package kr.hs.entrydsm.user.infrastructure.database;

import kr.hs.entrydsm.user.entity.user.User;
import kr.hs.entrydsm.user.entity.user.UserRepository;
import kr.hs.entrydsm.user.entity.user.enumeration.ApplicationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryManager extends CrudRepository<User, Long>, UserRepository {
        @Query(value = "select * from tbl_user as u join tbl_status as s on u.receipt_code = s.receipt_code where (u.receipt_code like ?1) and (?2 is null or u.is_daejeon = ?2) and (u.telephone_number like ?3) and (u.name like ?4) and ((u.headcount = 'IN_OF_HEADCOUNT' and ?5) or (u.headcount = 'OUT_OF_HEADCOUNT' and ?6) or (?5 = false and ?6 = false)) and ((u.application_type = 'COMMON' and ?7) or (u.application_type = 'MEISTER' and ?8) or (u.application_type = 'SOCIAL' and ?9)) and (?10 is null or s.is_printed_arrived = ?10)", nativeQuery = true)
    Page<User> findAllByUserInfo(String receiptCode, Boolean isDaejeon, String telephoneNumber, String name, boolean inOfHeadcount, boolean outOfHeadcount, boolean isCommon, boolean isMeister, boolean isSocial, Boolean isPrintedArrivedQuery, Pageable pageable);

    @Override
    User save(User user);

    List<User> findAllByIsDaejeonAndApplicationTypeAndStatus_IsSubmitTrue(boolean isDaejeon, ApplicationType applicationType);

}
