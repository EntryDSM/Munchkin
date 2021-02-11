package kr.hs.entrydsm.admin.usecase.dto;

import kr.hs.entrydsm.admin.domain.entity.enums.Type;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Builder
public class ScheduleRequest {

    @NotBlank
    private String year;

    @NotBlank
    private Type type;

    @NotBlank
    private String date;

}
