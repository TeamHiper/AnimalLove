package com.animal.AnimalLove.data.dto;

import com.animal.AnimalLove.data.entity.Comment;
import com.animal.AnimalLove.data.entity.Post;

public record CommentDto(
        Long commentId,
        String content,
        Long parentId,
        PostDto post
) {

    public static CommentDto from(Comment comment) {
        return new CommentDto(
                comment.getCommentId(),
                comment.getContent(),
                (comment.getParent() != null) ? comment.getParent().getCommentId() : null,
                PostDto.from(comment.getPost()));
    }

    public Comment toEntity(Comment parent, Post post) {
        return Comment.builder()
                .content(this.content)
                .parent(parent)
                .post(post)
                .user(post.getUser())
                .build();
    }

}
