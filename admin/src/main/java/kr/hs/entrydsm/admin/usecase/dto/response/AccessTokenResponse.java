package kr.hs.entrydsm.admin.usecase.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccessTokenResponse {

    @JsonProperty("access_token")
    private final String accessToken;

}
