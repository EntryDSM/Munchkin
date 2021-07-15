package kr.hs.entrydsm.user.usecase.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccessTokenResponse {
    private final String accessToken;
    private final String refreshToken;
}