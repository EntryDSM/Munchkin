package kr.hs.entrydsm.score.domain.entity.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QualificationExamCase {
    private long receiptCode;

    @Digits(integer = 3, fraction = 2)
    private BigDecimal averageScore;
}
