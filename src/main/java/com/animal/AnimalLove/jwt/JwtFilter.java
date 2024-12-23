package com.animal.AnimalLove.jwt;

import com.animal.AnimalLove.data.dto.CustomOAuth2User;
import com.animal.AnimalLove.data.dto.UserDto;
import com.animal.AnimalLove.data.entity.redis.RefreshToken;
import com.animal.AnimalLove.service.RefreshTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    public JwtFilter(JwtUtil jwtUtil, RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestUri = request.getRequestURI();

        if (requestUri.matches("^\\/login(?:\\/.*)?$")) {

            filterChain.doFilter(request, response);
            return;
        }
        if (requestUri.matches("^\\/oauth2(?:\\/.*)?$")) {

            filterChain.doFilter(request, response);
            return;
        }

        // 허용된 경로는 필터를 건너뜁니다.
        if (requestUri.startsWith("/swagger-ui/") ||
                requestUri.startsWith("/v3/api-docs/") ||
                requestUri.startsWith("/swagger-resources/") ||
                requestUri.startsWith("/webjars/") ||
                requestUri.startsWith("/api/v1/post/list")||
                requestUri.startsWith("/api/v1/post/detail")) {
            filterChain.doFilter(request, response); // 다음 필터로 진행
            return;
        }
        String accessToken = request.getHeader("Authorization");
        String refreshToken = null;

        if (request.getCookies() != null) {
            Map<String, String> tokens = Arrays.stream(request.getCookies())
                    .filter(cookie -> "RefreshToken".equals(cookie.getName())) // 필요한 쿠키 필터링
                    .collect(Collectors.toMap(Cookie::getName, Cookie::getValue)); // 쿠키 이름과 값을 맵으로 변환


            refreshToken = tokens.get("RefreshToken"); // Refresh Token
        }

        if (refreshToken == null) {
            //System.out.println("token null");
            filterChain.doFilter(request, response);
            //조건이 해당되면 메소드 종료 (필수)
            return;
        }

//        if (accessToken == null) {
//            //System.out.println("token null");
//            filterChain.doFilter(request, response);
//            //조건이 해당되면 메소드 종료 (필수)
//            return;
//        }

        //토큰 소멸 시간 검증
        // refreshToken 만료되면 재로그인
        if (jwtUtil.isExpired(refreshToken)) {
            Cookie refreshCookie = new Cookie("RefreshToken", null);
            refreshCookie.setMaxAge(0);
            refreshCookie.setSecure(true);
            refreshCookie.setPath("/");
            response.addCookie(refreshCookie);

            filterChain.doFilter(request, response);

            //조건이 해당되면 메소드 종료 (필수)
            return;
        }

        // accessToken 만료시 재발급
        if(jwtUtil.isExpired(accessToken)){
            RefreshToken ref = refreshTokenService.getRefreshToken(refreshToken).orElseThrow(()
                    -> new RuntimeException("db에 저장된 토큰이 없습니다"));

            accessToken = jwtUtil.createJwt("accessToken",ref.getUsername(), ref.getRole(), 60*60*60L);
            request.setAttribute("newAccessToken", accessToken);
        }

        //토큰에서 username과 role 획득
        String username = jwtUtil.getUsername(accessToken);
        String role = jwtUtil.getRole(accessToken);

        //userDTO를 생성하여 값 set
        UserDto userDto = UserDto.ofJwt(username,role);

        //UserDetails에 회원 정보 객체 담기
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDto);

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());

        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);


    }
}
