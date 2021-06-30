package kr.hs.entrydsm.application.usecase.dto.application.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.hs.entrydsm.application.usecase.dto.application.Information;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GraduatedInformationResponse extends Information {

    private String schoolTel;

    private String schoolCode;

    private String schoolName;

    private String studentNumber;

    @JsonProperty(value = "is_graduated")
    protected boolean isGraduated;

    public void setIsGraduated(boolean isGraduated) {
        this.isGraduated = isGraduated;
    }

    public GraduatedInformationResponse setInformation(Information information) {
        this.name = information.getName();
        this.sex = information.getSex();
        this.birthday = information.getBirthday();
        this.parentName = information.getParentName();
        this.parentTel = information.getParentTel();
        this.telephoneNumber = information.getTelephoneNumber();
        this.homeTel = information.getHomeTel();
        this.address = information.getAddress();
        this.detailAddress = information.getDetailAddress();
        this.postCode = information.getPostCode();
        this.photoFileName = information.getPhotoFileName();
        return this;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

}
