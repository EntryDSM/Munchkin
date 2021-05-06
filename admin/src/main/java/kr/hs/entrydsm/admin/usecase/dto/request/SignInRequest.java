package kr.hs.entrydsm.admin.usecase.dto.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class SignInRequest {

    @NotBlank
    private String id;

    @NotBlank
    private String password;

}
