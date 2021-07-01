package kr.hs.entrydsm.admin.usecase.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {

    private String accessToken;

    private String refreshToken;

}
