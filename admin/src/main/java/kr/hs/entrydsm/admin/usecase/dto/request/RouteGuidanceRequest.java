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

    private double endX;

    private double endY;

    private double startX;

    private double startY;

    private int totalValue;

}
