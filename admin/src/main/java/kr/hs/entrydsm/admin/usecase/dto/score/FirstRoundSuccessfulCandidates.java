package kr.hs.entrydsm.admin.usecase.dto.score;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class FirstRoundSuccessfulCandidates {

    List<Long> DaejeonCommonApplicants;

    List<Long> NationwideCommonApplicants;

    List<Long> DaejeonMeisterApplicants;

    List<Long> NationwideMeisterApplicants;

    List<Long> DaejeonSocialApplicants;

    List<Long> NationwideSocialApplicants;

}
