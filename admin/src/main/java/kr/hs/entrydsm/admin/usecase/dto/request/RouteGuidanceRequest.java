package kr.hs.entrydsm.admin.usecase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteGuidanceRequest {

    private double lng;

    private double lat;

    private double startX;

    private double startY;

    private int totalValue;

}
