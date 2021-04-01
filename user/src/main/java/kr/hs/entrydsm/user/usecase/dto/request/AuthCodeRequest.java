package kr.hs.entrydsm.user.usecase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthCodeRequest {

    @Pattern(regexp = "^[0-9]{11}$")
    private String phoneNumber;

    @Pattern(regexp = "\\d{6}")
    private String code;

}
