package kr.hs.entrydsm.application.usecase.dto.application.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Information {

    protected static final String TEL_REGEXP = "^\\d{3}\\d{3,4}\\d{4}$";

    @Pattern(regexp = "^(MALE|FEMALE)$", message = "INVALID SEX")
    protected String sex;

    @Length(min = 8, max = 8, message = "INVALID DATE")
    protected String birthday;

    @Length(max = 5, message = "TOO LONG NAME")
    protected String parentName;

    @NotEmpty(message = "telephone_number는 Null 또는 공백을 허용하지 않습니다.")
    @Pattern(regexp = TEL_REGEXP, message = "INVALID TEL")
    protected String telephoneNumber;

    @NotEmpty(message = "parent_tel은 Null 또는 공백을 허용하지 않습니다.")
    @Length(max = 11)
    @Pattern(regexp = TEL_REGEXP, message = "INVALID TEL")
    protected String parentTel;

    @NotEmpty(message = "home_tel은 Null 또는 공백을 허용하지 않습니다.")
    @Length(max = 11)
    @Pattern(regexp = TEL_REGEXP, message = "INVALID TEL")
    protected String homeTel;

    @NotEmpty(message = "address는 Null 또는 공백을 허용하지 않습니다.")
    @Length(max = 300, message = "TOO LONG ADDRESS")
    protected String address;

    @NotEmpty(message = "detailAddress는 Null 또는 공백을 허용하지 않습니다.")
    @Length(max = 100, message = "TOO LONG DETAIL_ADDRESS")
    protected String detailAddress;

    @Length(min = 5, max = 5, message = "INVALID POST_CODE")
    protected String postCode;

    protected String photoFileName;

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

}
