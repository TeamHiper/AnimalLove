package com.animal.AnimalLove.oauth2;

import com.animal.AnimalLove.jwt.JwtUtil;
import com.animal.AnimalLove.service.RefreshTokenService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CustomLogoutHandler implements LogoutHandler {
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    public CustomLogoutHandler(final JwtUtil jwtUtil, final RefreshTokenService refreshTokenService) {
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        Map<String, String> tokens = Arrays.stream(request.getCookies())
                .filter(cookie -> "Authorization".equals(cookie.getName()) || "RefreshToken".equals(cookie.getName())) // 필요한 쿠키 필터링
                .collect(Collectors.toMap(Cookie::getName, Cookie::getValue)); // 쿠키 이름과 값을 맵으로 변환

        String refreshToken = tokens.get("Authorization"); // Refresh Token

        System.out.println(" ======= refreshToken : " + refreshToken);
        // 디비에 토큰삭제
        refreshTokenService.deleteRefreshToken(refreshToken);

    }
}



