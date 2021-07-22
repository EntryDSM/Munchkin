package kr.hs.entrydsm.admin.entity.refreshtoken;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@RedisHash(value = "refresh_token")
public class AdminRefreshToken implements Serializable {

    @Id
    private String id;

    @Indexed
    private String refreshToken;

    @TimeToLive
    private Long refreshExp;

    public AdminRefreshToken update(Long refreshExp) {
        this.refreshExp = refreshExp;
        return this;
    }

}
