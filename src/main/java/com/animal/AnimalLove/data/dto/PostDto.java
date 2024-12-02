package com.animal.AnimalLove.data.dto;

import com.animal.AnimalLove.data.entity.Image;
import com.animal.AnimalLove.data.entity.Post;
import com.animal.AnimalLove.data.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public record PostDto(
        Long postId,
        String content,
        UserDto user, // User -> UserDto로 변환
        List<String> imageUrls

) {

    public static PostDto of(Long postId, String content, UserDto user, List<String> imageUrls) {
        return new PostDto(postId, content, user,imageUrls);
    }

    public static PostDto from(Post post){
        return new PostDto(
                post.getPostId(),
                post.getContent(),
                UserDto.from(post.getUser()), // User -> UserDto로 변환
                post.getImages().stream()
                        .map(Image::getUrl)
                        .collect(Collectors.toList())
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
