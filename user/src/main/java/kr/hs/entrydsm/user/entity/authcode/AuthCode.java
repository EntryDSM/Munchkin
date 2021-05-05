package kr.hs.entrydsm.user.entity.authcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@AllArgsConstructor
@RedisHash
public class AuthCode {

    @Id
    private final String phoneNumber;

    private String code;

    private boolean isVerified;

    @TimeToLive
    private long ttl;

    public AuthCode updateAuthCode(String code, long ttl) {
        this.code = code;
        this.ttl = ttl;
        return this;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public AuthCode verify() {
        this.isVerified = true;
        return this;
    }

}
