package com.animal.AnimalLove.controller;

import com.animal.AnimalLove.constants.ApiUrlConstants;
import com.animal.AnimalLove.data.entity.Image;
import com.animal.AnimalLove.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping(ApiUrlConstants.API_V1_IMAGE_UPLOAD)
    public ResponseEntity<Image> uploadImage(@RequestParam Long savedPostId ,@RequestParam("file") MultipartFile file) {
        try {
            Image savedImage = imageService.uploadImage(savedPostId,file);
            return ResponseEntity.ok(savedImage);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
