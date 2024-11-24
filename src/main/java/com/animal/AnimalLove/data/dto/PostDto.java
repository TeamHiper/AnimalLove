package com.animal.AnimalLove.data.dto;

import com.animal.AnimalLove.data.entity.Post;
import com.animal.AnimalLove.data.entity.User;

public record PostDto(
        Long postId,
        String content,
        byte[] image,
        UserDto user // User -> UserDto로 변환

) {

    public static PostDto of(Long postId, String content, byte[] image, UserDto user) {
        return new PostDto(postId, content, image, user);
    }

    public static PostDto from(Post post){
        return new PostDto(
                post.getPostId(),
                post.getContent(),
                post.getImage(),
                UserDto.from(post.getUser()) // User -> UserDto로 변환
        );
    }

    public Post toEntity(){
        return Post.builder()
                .postId(postId)
                .content(content)
                .image(image)
                .user(user.toEntity())
                .build();
    }

    // user 정보를 수정. 나머지는 기존 정보 그대로
    public Post toEntityWithUser(User user){
        return Post.builder()
                .postId(postId)
                .content(content)
                .image(image)
                .user(user)
                .build();
    }

}
