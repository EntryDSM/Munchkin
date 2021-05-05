package kr.hs.entrydsm.admin.integrate.user;

import kr.hs.entrydsm.admin.usecase.dto.ExcelApplicant;

import java.util.List;

public interface ExcelApplicantRepository {
    List<ExcelApplicant> findAllForExcel();
}