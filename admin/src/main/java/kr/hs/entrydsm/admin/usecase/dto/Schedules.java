package kr.hs.entrydsm.admin.usecase.dto;

import kr.hs.entrydsm.admin.entity.schedule.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedules {

    private Type type;

    private String date;

}
