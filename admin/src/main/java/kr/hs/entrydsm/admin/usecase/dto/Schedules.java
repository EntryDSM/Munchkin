package kr.hs.entrydsm.admin.usecase.dto;

import kr.hs.entrydsm.admin.entity.schedule.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedules {

    private String year;

    private Type type;

    private String date;

}
