package kr.hs.entrydsm.admin.usecase.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpRequest {

    private String id;

    private String password;

    private String name;

    private String permission;

}
