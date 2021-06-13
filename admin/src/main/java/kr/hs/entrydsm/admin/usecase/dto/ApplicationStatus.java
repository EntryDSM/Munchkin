package kr.hs.entrydsm.admin.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationStatus {

    private List<BigDecimal> commonScore; //일반 전형 성적 점수 리스트

    private List<BigDecimal> meisterScore; //마이스터 전형 성적 점수 리스트

    private List<BigDecimal> specialScore; //사회통합 전형 성적 점수 리스트

}