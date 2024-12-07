package com.animal.AnimalLove.controller;

import com.animal.AnimalLove.constants.ApiUrlConstants;
import com.animal.AnimalLove.data.dto.ImageDto;
import com.animal.AnimalLove.data.dto.PostDto;
import com.animal.AnimalLove.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postsService;

    @Operation(summary = "게시물 등록", description = "새로운 게시물 등록")
    @PostMapping(ApiUrlConstants.API_V1_POST_REGISTER)
    public ResponseEntity<Long> postRegister(@RequestBody PostDto postDto,
                                             @RequestParam(name = "url")String url,
                                             @RequestParam(name = "publicId") String publicId) {
        Long savedPostId = postsService.registerPost(postDto,url,publicId);
        return ResponseEntity.ok().body(savedPostId);
    }

    @Operation(summary = "게시물 상세조회")
    @GetMapping(ApiUrlConstants.API_V1_POST_DETAIL+"/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable(name = "postId") Long postId) {
        PostDto postDto = postsService.getPost(postId);
        return ResponseEntity.ok().body(postDto);
    }

    @Operation(summary = "게시물 리스트조회")
    @GetMapping(ApiUrlConstants.API_V1_POST_LIST)
    public ResponseEntity<List<PostDto>> getPostList(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        List<PostDto> postDto = postsService.getPostList(page,size);
        return ResponseEntity.ok().body(postDto);
    }


    @Operation(summary = "게시물 수정", description = "기존 게시물 수정")
    @PostMapping(ApiUrlConstants.API_V1_POST_UPDATE)
    public ResponseEntity<Integer> postUpdate(@RequestBody PostDto postDto,
                                              @RequestParam(name = "url")String url,
                                              @RequestParam(name = "publicId") String publicId) {

        int result = postsService.updatePost(postDto,url,publicId);
        return ResponseEntity.ok(result);
    }

}
