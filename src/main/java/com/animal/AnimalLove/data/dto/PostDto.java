package com.animal.AnimalLove.data.dto;

import com.animal.AnimalLove.data.entity.Post;
import com.animal.AnimalLove.data.entity.User;


public record PostDto(
        Long postId,
        String content,
        byte[] image,
        User user

) {

    public static PostDto of(Long postId, String content, byte[] image, User user) {
        return new PostDto(postId, content, image, user);
    }

    public static PostDto from(Post post){
        return new PostDto(
                post.getPostId(),
                post.getContent(),
                post.getImage(),
                post.getUser()
        );
    }

    public Post toEntity(){
        return Post.builder()
                .postId(postId)
                .content(content)
                .image(image)
                .user(user)
                .build();
    }

    public Post toEntityWithUser(User user){
        return Post.builder()
                .postId(postId)
                .content(content)
                .image(image)
                .user(user)
                .build();
    }
}
