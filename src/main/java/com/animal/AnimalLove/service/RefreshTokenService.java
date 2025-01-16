package com.animal.AnimalLove.service;

import com.animal.AnimalLove.data.entity.redis.RefreshToken;
import com.animal.AnimalLove.data.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    // Refresh Token 저장
    public void saveRefreshToken(String refreshToken, String email, String role, Long expiration) {
        RefreshToken entity = new RefreshToken(refreshToken, email, role, expiration);
        refreshTokenRepository.save(entity);
    }

    // username조회
    public Optional<RefreshToken> getRefreshToken(String refreshToken) {
        return refreshTokenRepository.findById(refreshToken);
    }

    // Refresh Token 삭제
    public void deleteRefreshToken(String email) {
        refreshTokenRepository.deleteById(email);
    }
}
