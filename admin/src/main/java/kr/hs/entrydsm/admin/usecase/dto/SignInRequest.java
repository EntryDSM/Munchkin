package kr.hs.entrydsm.admin.usecase.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInRequest {

    private String id;

    private String password;

}
