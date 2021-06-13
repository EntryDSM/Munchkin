package kr.hs.entrydsm.user.usecase.dto.response;

import kr.hs.entrydsm.user.entity.user.enumeration.ApplicationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserStatusResponse {
    private String name;
    private String phoneNumber;
    private Boolean isSubmit;
    private Boolean isPrintedArrived;
    private ApplicationType applicationType;
    private int selfIntroduce;
    private int studyPlan;
}
