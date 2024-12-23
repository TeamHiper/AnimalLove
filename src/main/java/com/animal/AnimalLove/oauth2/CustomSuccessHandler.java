package com.animal.AnimalLove.oauth2;

import com.animal.AnimalLove.data.dto.CustomOAuth2User;
import com.animal.AnimalLove.jwt.JwtUtil;
import com.animal.AnimalLove.service.RefreshTokenService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    public CustomSuccessHandler(JwtUtil jwtUtil, RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //OAuth2User
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        String username = customUserDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        String accessToken = jwtUtil.createJwt("accessToken",username, role, 60*60*60L);
        String refreshToken = jwtUtil.createJwt("refreshToken","example", role, 604800000L);
        refreshTokenService.saveRefreshToken(refreshToken,username, role, 604800000L);

        response.addCookie(createCookie("RefreshToken", refreshToken));
       // response.sendRedirect("http://localhost:3000/"+accessToken);
        response.sendRedirect("http://localhost:3000/oauth2/redirect?accessToken="+accessToken);

    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60 * 60 * 24 * 7); // 7일
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

}
