package kr.hs.entrydsm.application.usecase.dto.application.response;

import kr.hs.entrydsm.application.usecase.dto.application.Information;
import lombok.*;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
public class GedInformationResponse extends Information {

    @NotNull
    @DecimalMin(value = "60.0")
    @DecimalMax(value = "100.0")
    @Digits(integer = 3, fraction = 2)
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