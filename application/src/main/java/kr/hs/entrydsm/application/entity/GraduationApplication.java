package kr.hs.entrydsm.application.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "tbl_graduation_application")
public class GraduationApplication extends Application {

    private Boolean isGraduated;

    @Column(length = 5)
    private String studentNumber;

    @Getter(AccessLevel.NONE)
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

    private LocalDate graduateAt;

    @Builder
    public GraduationApplication(long receiptCode, boolean isGraduated, String studentNumber, School school,
                                 String schoolTel, int volunteerTime, int dayAbsenceCount, int lectureAbsenceCount,
                                 int latenessCount, int earlyLeaveCount, String koreanScore, String socialScore,
                                 String historyScore, String mathScore, String scienceScore, String englishScore,
                                 String techAndHomeScore, LocalDate graduateAt) {
        super(receiptCode);
        this.isGraduated = isGraduated;
        this.studentNumber = studentNumber;
        this.school = school;
        this.schoolTel = schoolTel;
        this.volunteerTime = volunteerTime;
        this.dayAbsenceCount = dayAbsenceCount;
        this.lectureAbsenceCount = lectureAbsenceCount;
        this.latenessCount = latenessCount;
        this.earlyLeaveCount = earlyLeaveCount;
        this.koreanScore = koreanScore;
        this.socialScore = socialScore;
        this.historyScore = historyScore;
        this.mathScore = mathScore;
        this.scienceScore = scienceScore;
        this.englishScore = englishScore;
        this.techAndHomeScore = techAndHomeScore;
        this.graduateAt = graduateAt;
    }

    @Override
    public boolean isGraduation() {
        return true;
    }

    public String getSchoolName() {
        return school.getName();
    }

    public String getSchoolCode() {
        return school.getCode();
    }

    public String getSchoolClass() {
        String schoolClass = null;
        if (studentNumber != null && !studentNumber.isBlank()) {
            int classNumber = Integer.parseInt(studentNumber.substring(1, 3));
            schoolClass = Integer.toString(classNumber);
        }
        return schoolClass;
    }

    public void setSchoolTel(String schoolTel){
        this.schoolTel = schoolTel;
    }

    public void setSchool(School school) {
        this.school = school;
    }

}
