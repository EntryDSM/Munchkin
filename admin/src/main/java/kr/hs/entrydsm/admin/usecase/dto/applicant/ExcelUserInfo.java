package kr.hs.entrydsm.admin.usecase.dto.applicant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExcelUserInfo {

    private String yearOfGraduation;

    private String middleSchoolStudentNumber;

    private String middleSchool;

}
