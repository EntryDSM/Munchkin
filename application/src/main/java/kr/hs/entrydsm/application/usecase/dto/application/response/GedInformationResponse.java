package kr.hs.entrydsm.application.usecase.dto.application.response;

import kr.hs.entrydsm.application.usecase.dto.application.Information;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class GedInformationResponse extends Information {

    private BigDecimal gedAverageScore;

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public GedInformationResponse setInformation(Information information) {
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

}
