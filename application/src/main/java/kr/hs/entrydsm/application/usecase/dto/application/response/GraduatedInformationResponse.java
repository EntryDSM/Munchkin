package kr.hs.entrydsm.application.usecase.dto.application.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class InformationResponse {

    private String name;

    private String sex;

    private String birthday;

    private String parentName;

    private String telephoneNumber;

    private String parentTel;

    private String homeTel;

    private String schoolTel;

    private String address;

    private String detailAddress;

    private String postCode;

    private String photoFileName;

    private String schoolCode;

    private String studentNumber;

    private boolean isGraduated;

    public void setSchoolCode(String schoolCode){
        this.schoolCode = schoolCode;
    }

    public void setSchoolTel(String schoolTel){
        this.schoolTel = schoolTel;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setIsGraduated(boolean isGraduated) {
        this.isGraduated = isGraduated;
    }

}
