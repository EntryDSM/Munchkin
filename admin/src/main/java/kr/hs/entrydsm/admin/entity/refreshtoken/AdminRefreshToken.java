package kr.hs.entrydsm.admin.entity.refreshtoken;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "refresh_token")
public class AdminRefreshToken implements Serializable {

    @Id
    private String id;

    @Indexed
    private String refreshToken;

    @Indexed
    private Long refreshExp;

    public AdminRefreshToken update(Long refreshExp) {
        this.refreshExp = refreshExp;
        return this;
    }

}
