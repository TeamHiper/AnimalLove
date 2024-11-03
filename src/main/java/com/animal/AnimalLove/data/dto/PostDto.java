package com.animal.AnimalLove.data.dto;

import com.animal.AnimalLove.data.entity.Posts;
import com.animal.AnimalLove.data.entity.Users;

public record PostDto(
        Long postId,
        String content,
        byte[] image,
        Users user

) {

    public static PostDto of(Long postId, String content, byte[] image, Users user) {
        return new PostDto(postId, content, image, user);
    }

    public static PostDto from(Posts post){
        return new PostDto(
                post.getPostId(),
                post.getContent(),
                post.getImage(),
                post.getUser()
        );
    }

    public Posts toEntity(){
        return Posts.builder()
                .postId(postId)
                .content(content)
                .image(image)
                .user(user)
                .build();
    }

    public Posts toEntityWithUser(Users user){
        return Posts.builder()
                .postId(postId)
                .content(content)
                .image(image)
                .user(user)
                .build();
    }
}
