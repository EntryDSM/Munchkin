package kr.hs.entrydsm.admin.usecase.dto.tmap;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Properties {

    @JsonProperty("totalDistance")
    private double totalDistance;

    @JsonProperty("totalTime")
    private int totalTime;

    @JsonProperty("totalFare")
    private int totalFare;

    @JsonProperty("totalFare")
    private int taxiFare;

}
