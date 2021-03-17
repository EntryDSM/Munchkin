package kr.hs.entrydsm.user.usecase.dto.request;

import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class SignupRequest extends AccountRequest {

    @Pattern(regexp = "[곽-힇]{2,10}")
    private final String name;

    @Pattern(regexp = "\\d{6}")
    private final String code;

    public SignupRequest(String phoneNumber, String password, String name, String code) {
        super(phoneNumber, password);
        this.name = name;
        this.code = code;
    }
    
}
