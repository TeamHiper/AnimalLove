package com.animal.AnimalLove.controller;

import com.animal.AnimalLove.constants.ApiUrlConstants;
import com.animal.AnimalLove.data.dto.PostDto;
import com.animal.AnimalLove.data.request.PostCreationRequest;
import com.animal.AnimalLove.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postsService;

    @Operation(summary = "게시물 등록", description = "새로운 게시물 등록")
    @PostMapping(ApiUrlConstants.API_V1_POST_REGISTER)
    public ResponseEntity<Long> postRegister(@RequestBody PostCreationRequest request) {
        Long savedPost = postsService.registerPost(request);
        return ResponseEntity.ok().body(savedPost);
    }

}
