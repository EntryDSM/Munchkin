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
public class AccountRequest {

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9])(?=.*[~!@#*_])[a-zA-Z0-9~!@#*_]{8,32}$")
    private String password;

}
