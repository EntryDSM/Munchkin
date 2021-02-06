package kr.hs.entrydsm.admin.usecase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {

    @JsonProperty("access-token")
    private String accessToken;

    @JsonProperty("refresh-token")
    private String refreshToken;

    @JsonProperty("refresh-exp")
    private Long refreshExp;

}
