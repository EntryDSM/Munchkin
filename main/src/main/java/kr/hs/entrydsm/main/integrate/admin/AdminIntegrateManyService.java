package kr.hs.entrydsm.main.integrate.admin;

import kr.hs.entrydsm.admin.entity.ExcelApplicant;
import kr.hs.entrydsm.admin.integrate.user.ExcelApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AdminIntegrateManyService implements ExcelApplicantRepository {

    @Override
    public List<ExcelApplicant> findAll() {
        return null;
    }

}
