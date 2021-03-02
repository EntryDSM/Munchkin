package kr.hs.entrydsm.admin.integrate.notification;

import kr.hs.entrydsm.admin.domain.entity.Admin;
import kr.hs.entrydsm.admin.domain.repository.AdminRepository;
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
