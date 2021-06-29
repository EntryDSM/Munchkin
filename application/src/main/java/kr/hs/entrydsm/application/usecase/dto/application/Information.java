package kr.hs.entrydsm.application.usecase.dto.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Information {

    protected static final String TEL_REGEXP = "^\\d{3}\\d{3,4}\\d{4}$";

    @NotEmpty
    protected String name;

    @Pattern(regexp = "^(MALE|FEMALE)$", message = "INVALID SEX")
    protected String sex;

    @Length(min = 8, max = 8, message = "INVALID DATE")
    protected String birthday;

    protected String parentName;

    @NotEmpty
    @Pattern(regexp = TEL_REGEXP, message = "INVALID TEL")
    protected String telephoneNumber;

    @Length(max = 11)
    @Pattern(regexp = TEL_REGEXP, message = "INVALID TEL")
    protected String parentTel;

    @Length(max = 11)
    @Pattern(regexp = TEL_REGEXP, message = "INVALID TEL")
    protected String homeTel;

    @Length(max = 300, message = "TOO LONG ADDRESS")
    protected String address;

    @Length(max = 100, message = "TOO LONG DETAIL_ADDRESS")
    protected String detailAddress;

    @Length(min = 5, max = 5, message = "INVALID POST_CODE")
    protected String postCode;

    protected String photoFileName;

}
