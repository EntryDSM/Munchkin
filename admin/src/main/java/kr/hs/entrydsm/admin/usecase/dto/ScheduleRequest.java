package kr.hs.entrydsm.admin.usecase.dto;

import kr.hs.entrydsm.admin.domain.entity.Type;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ScheduleRequest {

    private String year;

    private Type type;

    private LocalDate date;

}
