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
    @PostMapping(ApiUrlConstants.API_V1_LIKE_REGISTER)
    public ResponseEntity<Long> likeRegister(@RequestParam(name = "userId") Long userId,
                                             @RequestParam(name = "postId") Long postId){
        Long likedPostId = likeService.likeRegister(userId,postId);
        return ResponseEntity.ok().body(likedPostId);
    }

    @Operation(summary = "좋아요 삭제", description = "좋아요 삭제")
    @PostMapping(ApiUrlConstants.API_V1_LIKE_DELETE)
    public ResponseEntity<Long> likeDelete(@RequestParam(name = "likeId") Long likeId){
        Long deletedPostId = likeService.likeDelete(likeId);
        return ResponseEntity.ok().body(deletedPostId);
    }

}
