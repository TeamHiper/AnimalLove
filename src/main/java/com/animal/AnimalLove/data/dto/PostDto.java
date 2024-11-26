package com.animal.AnimalLove.data.dto;

import com.animal.AnimalLove.data.entity.Post;
import com.animal.AnimalLove.data.entity.User;

public record PostDto(
        Long postId,
        String content,
        UserDto user // User -> UserDto로 변환

) {

    public static PostDto of(Long postId, String content, UserDto user) {
        return new PostDto(postId, content, user);
    }

    public static PostDto from(Post post){
        return new PostDto(
                post.getPostId(),
                post.getContent(),
                UserDto.from(post.getUser()) // User -> UserDto로 변환
        );
    }

    public Post toEntity(){
        return Post.builder()
                .content(content)
                .user(user.toEntity())
                .build();
    }

    public Post toEntityWithUser(User user){
        return Post.builder()
                .postId(postId)
                .content(content)
                .user(user)
                .build();
    }
}
