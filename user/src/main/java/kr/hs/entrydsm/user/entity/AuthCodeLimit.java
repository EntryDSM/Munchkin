package kr.hs.entrydsm.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@AllArgsConstructor
@RedisHash
public class AuthCodeLimit {

    @Id
    private final String phoneNumber;

    private int count;

    @TimeToLive
    private long ttl;

    public AuthCodeLimit updateAuthCode(long ttl) {
        this.count++;
        this.ttl = ttl;
        return this;
    }

}
