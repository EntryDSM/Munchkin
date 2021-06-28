package kr.hs.entrydsm.admin.usecase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coordinate {

    private String lat;

    private String lon;

    @JsonProperty("city_do")
    private String cityDo;

    @JsonProperty("gu_gun")
    private String guGun;

    @JsonProperty("eup_myun")
    private String eupMyun;

    private String legalDong;

    private String adminDong;

    private String ri;

    private String bunji;

    private String buildingName;

    private String buildingDong;

    private String latEntr;

    private String lonEntr;

    private String newRoadName;

    private String newBuildingIndex;

    private String zipcode;

    public String getBuildingDong() {
        return buildingDong;
    }
}
