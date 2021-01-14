package kr.hs.entrydsm.application.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_qualification_exam_application")
public class QualificationExamApplication {

    @Id
    private Long receiptCode;

    @Digits(integer = 3, fraction = 2)
    private BigDecimal averageScore;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime modifiedAt;
}
