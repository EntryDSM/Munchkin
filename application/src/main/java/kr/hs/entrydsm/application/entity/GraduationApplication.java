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

    private LocalDate graduatedAt;

    public GraduationApplication(long receiptCode) {
        super(receiptCode);
    }

    @Builder
    public GraduationApplication(long receiptCode, boolean isGraduated, String studentNumber, School school,
                                 String schoolTel, LocalDate graduatedAt) {
        super(receiptCode);
        this.isGraduated = isGraduated;
        this.studentNumber = studentNumber;
        this.school = school;
        this.schoolTel = schoolTel;
        this.graduatedAt = graduatedAt;
    }

    @Override
    public boolean isGraduation() {
        return true;
    }

    public String getSchoolName() {
        return school == null ? null : school.getName();
    }

    public String getSchoolCode() {
        return school == null ? null : school.getCode();
    }

    public String getSchoolClass() {
        String schoolClass = null;
        if (studentNumber != null && !studentNumber.isBlank()) {
            int classNumber = Integer.parseInt(studentNumber.substring(1, 3));
            schoolClass = Integer.toString(classNumber);
        }
        return schoolClass;
    }

}
