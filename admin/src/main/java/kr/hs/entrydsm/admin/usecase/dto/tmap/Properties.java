package kr.hs.entrydsm.admin.usecase.dto.tmap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Properties {

    private double totalDistance;

    private int totalTime;

    private int totalFare;

    private int taxiFare;

}
