package kr.hs.entrydsm.application.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MiddleSchoolInfo {

    private Integer yearOfGraduation;

    private String middleSchoolStudentNumber;

    private String middleSchool;

}
