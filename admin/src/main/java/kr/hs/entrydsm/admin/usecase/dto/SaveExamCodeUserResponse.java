package kr.hs.entrydsm.admin.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveExamCodeUserResponse {

    private String applicationType;

    private boolean isDaejeon;

    private String examCode;

    private String address;

    private long receiptCode;

    private double distance;

    public void updateExamCode(String examCode) {
        this.examCode = examCode;
    }

    public void updateDistance(double distance) {
        this.distance = distance;
    }


}
