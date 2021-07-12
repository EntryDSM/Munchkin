package kr.hs.entrydsm.score.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class FirstRoundSuccessfulCandidate {

    List<Long> DaejeonCommonApplicants;

    List<Long> NationwideCommonApplicants;

    List<Long> DaejeonMeisterApplicants;

    List<Long> NationwideMeisterApplicants;

    List<Long> DaejeonSocialApplicants;

    List<Long> NationwideSocialApplicants;

}
