package kr.hs.entrydsm.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@AllArgsConstructor
@RedisHash
public class AuthCode {

    @Id
    private final String phoneNumber;

    @Indexed
    private final String code;

    @TimeToLive
    private long ttl;

}
