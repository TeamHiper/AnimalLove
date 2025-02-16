package com.animal.AnimalLove.controller;

import com.animal.AnimalLove.constants.ApiUrlConstants;
import com.animal.AnimalLove.data.dto.CustomOAuth2User;
import com.animal.AnimalLove.data.dto.ImageDto;
import com.animal.AnimalLove.data.dto.UserDto;
import com.animal.AnimalLove.data.entity.Image;
import com.animal.AnimalLove.service.ImageService;
import com.animal.AnimalLove.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    private final UserService userService;

    @PostMapping(ApiUrlConstants.API_V1_IMAGE_UPLOAD)
    public ResponseEntity<ImageDto> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            ImageDto savedImage = imageService.uploadImage(file);
            return ResponseEntity.ok(savedImage);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping(ApiUrlConstants.API_V1_IMAGE_UPLOAD_PROFILE)
    public ResponseEntity<UserDto> uploadProfileImage(@RequestParam("file") MultipartFile file,
                                                       @RequestHeader(value = "Authorization", required = false) String authorizationHeader
    ) {
        // 메서드로 빼도 될듯
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Principal에서 사용자 정보 추출
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        try {
            ImageDto savedImage = imageService.uploadImage(file);
            UserDto userInfo = userService.updateProfileImage(customOAuth2User.getEmail(),savedImage.url());
            return ResponseEntity.ok(userInfo);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

}
