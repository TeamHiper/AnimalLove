package com.animal.AnimalLove.controller;

import com.animal.AnimalLove.constants.ApiUrlConstants;
import com.animal.AnimalLove.data.dto.CommentDto;
import com.animal.AnimalLove.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 등록", description = "새로운 댓글 등록")
    @PostMapping(ApiUrlConstants.API_V1_COMMENT_REGISTER)
    public ResponseEntity<Long> commentRegister(@RequestBody CommentDto dto) {
        Long commentId = commentService.registerComment(dto);
        return ResponseEntity.ok(commentId);
    }

    @Operation(summary = "댓글 상세조회")
    @GetMapping(ApiUrlConstants.API_V1_COMMENT_DETAIL + "/{postId}")
    public ResponseEntity<List<CommentDto>> getcomment(@PathVariable(name = "postId") Long postId) {
        List<CommentDto> comments = commentService.getCommentsByPost(postId);
        return ResponseEntity.ok(comments);
    }

    @Operation(summary = "댓글 수정", description = "기존 댓글 수정")
    @PostMapping(ApiUrlConstants.API_V1_COMMENT_UPDATE)
    public ResponseEntity<Integer> commentUpdate(@RequestBody CommentDto dto) {

        int result = commentService.updateComment(dto);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "댓글 삭제", description = "기존 댓글 삭제")
    @PostMapping(ApiUrlConstants.API_V1_COMMENT_DELETE + "/{commentId}")
    public ResponseEntity<Integer> commentDelete(@PathVariable(name = "commentId") Long commentId) {
        int result = commentService.deleteComment(commentId);
        return ResponseEntity.ok(result);
    }

}
