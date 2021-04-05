package kr.hs.entrydsm.application.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
