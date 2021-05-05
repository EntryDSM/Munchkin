package kr.hs.entrydsm.admin.usecase.dto.request;

import kr.hs.entrydsm.admin.entity.schedule.Type;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

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
