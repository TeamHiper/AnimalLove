package com.animal.AnimalLove.data.dto;

import com.animal.AnimalLove.data.entity.Image;
import com.animal.AnimalLove.data.entity.Like;
import com.animal.AnimalLove.data.entity.Post;
import com.animal.AnimalLove.data.entity.User;

public record LikeDto(
        PostDto post,
        UserDto user
) {


    public static Like toEntity(Post post, User user){
        return Like.builder()
                .post(post)
                .user(user)
                .build();
    }

}

