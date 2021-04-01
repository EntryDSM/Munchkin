package kr.hs.entrydsm.user.usecase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest extends AccountRequest {

    @Pattern(regexp = "[곽-힇]{2,10}")
    private String name;

    @Pattern(regexp = "\\d{6}")
    private String code;

    public SignupRequest(String phoneNumber, String password, String name, String code) {
        super(phoneNumber, password);
        this.name = name;
        this.code = code;
    }

}
