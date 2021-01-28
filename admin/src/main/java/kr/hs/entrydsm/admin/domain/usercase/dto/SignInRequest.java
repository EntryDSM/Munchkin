package kr.hs.entrydsm.admin.domain.usercase.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInRequest {

    private Integer id;

    private String password;

}
