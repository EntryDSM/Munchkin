package kr.hs.entrydsm.user.usecase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthCodeRequest {

    @Email
    @NotEmpty
    private String email;

    @Pattern(regexp = "\\d{6}")
    @NotEmpty
    private String code;

}
