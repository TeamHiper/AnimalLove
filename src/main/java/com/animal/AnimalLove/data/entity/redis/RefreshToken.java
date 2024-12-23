package com.animal.AnimalLove.data.entity.redis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@RedisHash(value = "RefreshToken")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Service
public class RefreshToken implements Serializable {

    @Id // Redis에서 고유 키
    private String refreshToken; // Refresh Token 값

    private String username; // 사용자 식별자 (Key)
    private String role;
    private Long expiration; // 만료 시간 (Optional)


}
