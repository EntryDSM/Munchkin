package kr.hs.entrydsm.admin.usecase.dto;

import kr.hs.entrydsm.admin.entity.enums.Type;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class Schedules {

    private String year;

    private Type type;

    private LocalDate date;

}
