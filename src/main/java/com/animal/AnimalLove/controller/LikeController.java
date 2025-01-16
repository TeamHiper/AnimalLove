package com.animal.AnimalLove.controller;

import com.animal.AnimalLove.constants.ApiUrlConstants;
import com.animal.AnimalLove.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @Operation(summary = "좋아요 추가", description = "좋아요 추가")
    @PostMapping(ApiUrlConstants.API_V1_LIKE)
    public ResponseEntity<Boolean> likeYN(@RequestParam String email,
                                                @RequestParam Long postId){
        boolean likedYN = likeService.likeYN(email,postId);
        return ResponseEntity.ok().body(likedYN);
    }

    @Operation(summary = "좋아요 여부", description = "좋아요 여부")
    @PostMapping(ApiUrlConstants.API_V1_LIKE_CHECK)
    public ResponseEntity<Boolean> likeCheck(@RequestParam String email,
                                            @RequestParam Long postId){
        boolean likedYN = likeService.likeCheck(email,postId);
        return ResponseEntity.ok().body(likedYN);
    }

}
