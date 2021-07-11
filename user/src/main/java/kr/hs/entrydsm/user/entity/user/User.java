package kr.hs.entrydsm.user.entity.user;

import kr.hs.entrydsm.user.entity.status.Status;
import kr.hs.entrydsm.user.entity.user.enumeration.ApplicationRemark;
import kr.hs.entrydsm.user.entity.user.enumeration.ApplicationType;
import kr.hs.entrydsm.user.entity.user.enumeration.EducationalStatus;
import kr.hs.entrydsm.user.entity.user.enumeration.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long receiptCode;

    @Column(columnDefinition = "char(36)", nullable = false)
    private String email;

    @Column(columnDefinition = "char(60)", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private ApplicationType applicationType;

    @Enumerated(EnumType.STRING)
    private ApplicationRemark applicationRemark;

    @Enumerated(EnumType.STRING)
    private EducationalStatus educationalStatus;

    private Boolean isDaejeon;

    @Column(columnDefinition = "char(5)", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private LocalDate birthday;

    @Column(columnDefinition = "char(11)")
    private String telephoneNumber;

    @Column(columnDefinition = "char(5)")
    private String parentName;

    @Column(columnDefinition = "char(11)")
    private String parentTel;

    @Column(length = 300)
    private String address;

    @Column(length = 100)
    private String detailAddress;

    @Column(columnDefinition = "char(5)")
    private String postCode;

    @Column(columnDefinition = "char(45)")
    private String photoFileName;

    @Column(columnDefinition = "char(11)")
    private String homeTel;

    @Column(length = 1600)
    private String selfIntroduce;

    @Column(length = 1600)
    private String studyPlan;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Status status;

    public String getExamCode() {
        return status.getExamCode();
    }

    public User setExamCode(String examCode) {
        status.setExamCode(examCode);
        return this;
    }

    public User changePassword(String encodedPassword) {
        this.password = encodedPassword;
        return this;
    }

    public User setPrintedArrived(boolean isPrintedArrived) {
        this.status.setPrintedArrived(isPrintedArrived);
        return this;
    }

    public User updateUserApplication(EducationalStatus educationalStatus, ApplicationType applicationType,
                                      boolean isDaejeon, ApplicationRemark applicationRemark) {
        this.educationalStatus = educationalStatus;
        this.applicationType = applicationType;
        this.isDaejeon = isDaejeon;
        this.applicationRemark = applicationRemark;
        return this;
    }

    public void updateInformation(String name, String sex, LocalDate birthday,
                                  String parentName, String parentTel, String telephoneNumber, String homeTel,
                                  String address, String postCode, String photoFileName, String detailAddress) {
        this.name = name;
        this.sex = sex == null ? null : Sex.valueOf(sex);
        this.birthday = birthday;
        this.parentName = parentName;
        this.parentTel = parentTel;
        this.telephoneNumber = telephoneNumber;
        this.homeTel = homeTel;
        this.address = address;
        this.postCode = postCode;
        this.photoFileName = photoFileName;
        this.detailAddress = detailAddress;
    }

    public void updatePhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public void updateSelfIntroduce(String selfIntroduce) {
        if (!selfIntroduce.isBlank() && !selfIntroduce.isEmpty()) {
            this.selfIntroduce = selfIntroduce;
        }
    }

    public void updateStudyPlan(String studyPlan) {
        if (!studyPlan.isBlank() && !studyPlan.isEmpty()) {
            this.studyPlan = studyPlan;
        }
    }

    public User submitFinally() {
        this.status.setSubmitStatus(true);
        return this;
    }

    public void setSubmitFalse() {
        this.status.setSubmitStatus(false);
    }

}
