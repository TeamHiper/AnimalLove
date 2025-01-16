package com.animal.AnimalLove.controller;

import com.animal.AnimalLove.constants.ApiUrlConstants;
import com.animal.AnimalLove.data.dto.CustomOAuth2User;
import com.animal.AnimalLove.data.dto.PostDto;
import com.animal.AnimalLove.data.dto.UserDto;
import com.animal.AnimalLove.jwt.JwtUtil;
import com.animal.AnimalLove.service.PostService;
import com.animal.AnimalLove.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Operation(summary = "Mock 유저 등록", description = "Mock User 등록")
    @PostMapping(ApiUrlConstants.API_V1_USER_REGISTER+"/mock")
    public ResponseEntity<UserDto> mockUserRegister() {
        UserDto savedUser = userService.registerMockUser();
        return ResponseEntity.ok().body(savedUser);
    }
    
    @Operation(summary = "유저 등록", description = "새로운 유저를 등록")
    @PostMapping(ApiUrlConstants.API_V1_USER_REGISTER)
    public ResponseEntity<UserDto> userRegister(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.registerUser(userDto);
        return ResponseEntity.ok().body(savedUser);
    }

    @Operation(summary = "유저 정보 조회", description = "토큰으로 유저정보 조회")
    @GetMapping(ApiUrlConstants.API_V1_USER_GETUSER)
    public ResponseEntity<UserDto> userGetUser(@RequestParam("accessToken") String accessToken,
                                               @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                                               HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Principal에서 사용자 정보 추출
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        UserDto userInfo = userService.getUser(customOAuth2User.getEmail());

        // accesstoken 만료여부
        if(jwtUtil.isExpired(authorizationHeader)){
            String newAccessToken = (String) request.getAttribute("New-Access-Token");
            return ResponseEntity.ok()
                    .header("Authorization", newAccessToken)
                    .body(userInfo);
        }

        return ResponseEntity.ok()
                .header("Authorization", authorizationHeader)
                .body(userInfo);
    }

}
