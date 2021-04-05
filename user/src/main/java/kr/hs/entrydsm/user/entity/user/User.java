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

    @Column(columnDefinition = "char (11)", nullable = false)
    private String telephoneNumber;

    @Column(columnDefinition = "char(11)", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private ApplicationType applicationType;

    @Enumerated(EnumType.STRING)
    private ApplicationRemark applicationRemark;

    @Enumerated(EnumType.STRING)
    private EducationalStatus educationalStatus;

    private boolean isDaejeon;

    @Column(columnDefinition = "char(5)", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private LocalDate birthday;

    @Column(columnDefinition = "char(5)")
    private String parentName;

    @Column(columnDefinition = "char(11)")
    private String parentTel;

    @Column(length = 300)
    private String address;

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

    public User updateUserApplication(EducationalStatus educationalStatus, ApplicationType applicationType,
                                      boolean isDaejeon, ApplicationRemark applicationRemark) {
        this.educationalStatus = educationalStatus;
        this.applicationType = applicationType;
        this.isDaejeon = isDaejeon;
        this.applicationRemark = applicationRemark;
        return this;
    }

    public User updateUserInformation(String name, String sex, LocalDate birthday,
                                      String parentName, String parentTel, String telephoneNumber, String homeTel,
                                      String address, String postCode, String photoFileName) {
        this.name = name;
        this.sex = Sex.valueOf(sex);
        this.birthday = birthday;
        this.parentName = parentName;
        this.parentTel = parentTel;
        this.telephoneNumber = telephoneNumber;
        this.homeTel = homeTel;
        this.address = address;
        this.postCode = postCode;
        this.photoFileName = photoFileName;
        return this;
    }

}
