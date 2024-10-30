package com.animal.AnimalLove.controller;

import com.animal.AnimalLove.constants.ApiUrlConstants;
import com.animal.AnimalLove.data.dto.PostDto;
import com.animal.AnimalLove.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postsService;

    @Operation(summary = "게시물 등록", description = "새로운 게시물을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시물이 성공적으로 등록되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.")
    })
    @PostMapping(ApiUrlConstants.API_V1_POST_REGISTER)
    public ResponseEntity<PostDto> postRegister(@RequestBody PostDto postDto) {
        PostDto savedPost = postsService.registerPost(postDto);
        return ResponseEntity.ok().body(savedPost);
    }

}
