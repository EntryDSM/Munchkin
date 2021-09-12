package kr.hs.entrydsm.user.usecase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest extends AccountRequest {

    @Pattern(regexp = "[가-힇]{2,10}")
	@Length(min = 1, max = 5, message = "INVALID NAME")
    @NotNull
    private String name;

    public SignupRequest(String phoneNumber, String password, String name) {
        super(phoneNumber, password);
        this.name = name;
    }

}
