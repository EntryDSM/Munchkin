package kr.hs.entrydsm.application.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_graduation_application")
public class GraduationApplication extends Application {

    private Boolean isGraduated;

    @ManyToOne
    @JoinColumn(name = "school_code")
    private School school;

    @Column(length = 11)
    private String schoolTel;

    private Integer volunteerTime;

    private Integer dayAbsenceCount;

    private Integer lectureAbsenceCount;

    private Integer latenessCount;

    private Integer earlyLeaveCount;

    @Column(length = 6)
    private String koreanScore;

    @Column(length = 6)
    private String socialScore;

    @Column(length = 6)
    private String historyScore;

    @Column(length = 6)
    private String mathScore;

    @Column(length = 6)
    private String scienceScore;

    @Column(length = 6)
    private String englishScore;

    @Column(length = 6)
    private String techAndHomeScore;

    @Override
    public boolean isGraduation() {
        return true;
    }

    public String getSchoolName() {
        return school.getName();
    }
}
