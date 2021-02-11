package kr.hs.entrydsm.admin.integrate.notification;

import kr.hs.entrydsm.admin.domain.entity.Admin;
import kr.hs.entrydsm.admin.domain.entity.enums.Permission;
import kr.hs.entrydsm.admin.domain.repository.AdminRepository;
import kr.hs.entrydsm.admin.security.auth.AuthenticationFacade;
import kr.hs.entrydsm.admin.usecase.exception.AdminNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@RequiredArgsConstructor
@Repository
public class AdminExportManager implements AdminExportRepository {

    private final AdminRepository adminRepository;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public Admin findById(String id) {
        return adminRepository.findById(id)
                .orElseThrow(AdminNotFoundException::new);
    }

    @Override
    public boolean isTeacher() {
        Admin admin = adminRepository.findById(authenticationFacade.getUserId())
                .orElseThrow(AdminNotFoundException::new);

        return admin.getPermission().equals(Permission.TEACHER);
    }

}
