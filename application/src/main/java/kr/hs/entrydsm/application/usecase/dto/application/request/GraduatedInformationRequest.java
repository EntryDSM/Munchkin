package kr.hs.entrydsm.application.usecase.dto.application.request;

import kr.hs.entrydsm.application.usecase.dto.application.Information;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Getter
@Builder
@AllArgsConstructor
public class GraduatedInformationRequest extends Information {

    @Length(max = 11)
    @Pattern(regexp = TEL_REGEXP, message = "INVALID TEL")
    private final String schoolTel;

    @Length(max = 7, message = "INVALID SCHOOL_CODE")
    private final String schoolCode;

    @Length(max = 5, message = "INVALID STUDENT_NUMBER")
    private final String studentNumber;

}
