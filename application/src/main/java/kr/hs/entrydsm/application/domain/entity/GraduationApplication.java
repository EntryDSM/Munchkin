package kr.hs.entrydsm.application.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_graduation_application")
public class GraduationApplication {

    @Id
    private Long receiptCode;

    private Boolean isGraduated;

    @Column(length = 7)
    private String schoolCode;

    @Column(length = 11)
    private String schoolTel;

    private Integer volunteerTime;

    private Integer dayAbsenceCount;

    private Integer lectureAbsenceCount;

    private Integer latenessCount;

    private Integer earlyLeaveCount;

    @Column(length = 5)
    private String koreanScore;

    @Column(length = 5)
    private String socialScore;

    @Column(length = 5)
    private String historyScore;

    @Column(length = 5)
    private String mathScore;

    @Column(length = 5)
    private String scienceScore;

    @Column(length = 5)
    private String englishScore;

    @Column(length = 5)
    private String techAndHomeScore;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime modifiedAt;
}
