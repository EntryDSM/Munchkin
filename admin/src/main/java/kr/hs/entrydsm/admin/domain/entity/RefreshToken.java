package kr.hs.entrydsm.admin.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "refresh_token")
public class RefreshToken implements Serializable {

    @Id
    private String id;

    @Indexed
    private String refreshToken;

    @Indexed
    private Long refreshExp;

    public RefreshToken update(Long refreshExp) {
        this.refreshExp = refreshExp;
        return this;
    }

}
