package com.animal.AnimalLove.controller;

import com.animal.AnimalLove.constants.ApiUrlConstants;
import com.animal.AnimalLove.data.dto.LikeDto;
import com.animal.AnimalLove.data.dto.LikeResponseDto;
import com.animal.AnimalLove.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Operation(summary = "좋아요 숫자", description = "좋아요 숫자")
    @GetMapping(ApiUrlConstants.API_V1_LIKE_COUNT)
    public ResponseEntity<Integer> likeCount(@RequestParam Long postId){
        int likeCount = likeService.likeCount(postId);
        return ResponseEntity.ok().body(likeCount);
    }

    @Operation(summary = "좋아요 한 인원 리스트", description = "좋아요 한 인원 리스트")
    @GetMapping(ApiUrlConstants.API_V1_LIKE_WHO)
    public ResponseEntity<List<String>> likeWho(@RequestParam String email,
                                             @RequestParam Long postId){
        List<String> likedWho = likeService.likeWho(email,postId);
        return ResponseEntity.ok().body(likedWho);
    }

    @Operation(summary = "좋아요 목록", description = "좋아요 목록")
    @GetMapping(ApiUrlConstants.API_V1_LIKE_LIST)
    public ResponseEntity<List<LikeResponseDto>> likeList(@RequestParam Long userId){
        List<LikeResponseDto> likeList = likeService.likeList(userId);
        return ResponseEntity.ok().body(likeList);
    }

}
