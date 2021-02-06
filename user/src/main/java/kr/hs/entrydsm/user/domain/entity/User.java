package kr.hs.entrydsm.user.domain.entity;

import kr.hs.entrydsm.user.domain.entity.enumeration.ApplicationRemark;
import kr.hs.entrydsm.user.domain.entity.enumeration.ApplicationType;
import kr.hs.entrydsm.user.domain.entity.enumeration.EducationalStatus;
import kr.hs.entrydsm.user.domain.entity.enumeration.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;

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

    @CreatedBy
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.LAZY)
    private Status status;

}
