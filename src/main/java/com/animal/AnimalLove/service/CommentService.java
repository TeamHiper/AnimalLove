package com.animal.AnimalLove.service;

import com.animal.AnimalLove.data.dto.CommentDto;
import com.animal.AnimalLove.data.entity.Comment;
import com.animal.AnimalLove.data.entity.Post;
import com.animal.AnimalLove.data.repository.CommentRepository;
import com.animal.AnimalLove.data.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    // 댓글 등록
    @Transactional
    public Long registerComment(CommentDto dto) {
        Post post = postRepository.findById(dto.post().postId())
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        Comment parent = (dto.parentId() != null) ?
                commentRepository.findById(dto.parentId())
                        .orElseThrow(() -> new IllegalArgumentException("부모 댓글이 존재하지 않습니다."))
                : null;

        Comment comment = dto.toEntity(parent, post);
        return commentRepository.save(comment).getCommentId();
    }

    // 댓글 수정
    @Transactional
    public int updateComment(CommentDto dto) {
        Comment comment = commentRepository.findById(dto.commentId())
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        comment.updateContent(dto.content());
        commentRepository.save(comment);
        return 1;
    }

    // 댓글 삭제 (useYn을 N으로 변경)
    @Transactional
    public int deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

//        commentRepository.delete(comment);
        comment.setUseYn("N");
        return 1;
    }

    // 댓글 조회 (게시글 ID 기준)
    @Transactional(readOnly = true)
    public List<CommentDto> getCommentsByPost(Long postId) {
        List<Comment> comments = commentRepository.findByPost_PostId(postId);
        return comments.stream().map(CommentDto::from).toList();
    }
}
