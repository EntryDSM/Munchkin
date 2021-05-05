package kr.hs.entrydsm.admin.integrate.notification;

import kr.hs.entrydsm.admin.entity.admin.Admin;
import kr.hs.entrydsm.admin.entity.admin.AdminRepository;
import kr.hs.entrydsm.admin.usecase.exception.AdminNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@RequiredArgsConstructor
@Repository
public class AdminExportManager implements AdminExportRepository {

    private final AdminRepository adminRepository;

    @Override
    public Admin findById(String userId) {
        return adminRepository.findById(userId)
                .orElseThrow(AdminNotFoundException::new);
    }

}
