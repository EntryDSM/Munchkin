package kr.hs.entrydsm.admin.integrate.user;

import kr.hs.entrydsm.admin.entity.ExcelApplicant;

import java.util.List;

public interface ExcelApplicantRepository {
    List<ExcelApplicant> findAllForExcel();
}