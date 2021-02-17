package kr.hs.entrydsm.main.integrate.notification;

import kr.hs.entrydsm.admin.domain.entity.Admin;
import kr.hs.entrydsm.admin.integrate.notification.AdminExportRepository;
import kr.hs.entrydsm.notification.domain.entity.Teacher;
import kr.hs.entrydsm.notification.integrate.admin.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NotificationIntegrateAdminService implements TeacherRepository {

    private final AdminExportRepository adminExportRepository;

    @Override
    public Teacher findById(String userId) {
        Admin admin = adminExportRepository.findById(userId);
        return Teacher.builder()
                .id(admin.getId())
                .permission(String.valueOf(admin.getPermission()))
                .build();
    }

}
