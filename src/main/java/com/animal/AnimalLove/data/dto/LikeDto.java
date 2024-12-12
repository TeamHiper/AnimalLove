package com.animal.AnimalLove.data.dto;

import com.animal.AnimalLove.data.entity.Like;
import com.animal.AnimalLove.data.entity.User;

public record LikeDto(
        Long postId,
        UserDto user
) {


    public static Like toEntity(Long postId, User user){
        return Like.builder()
                .postId(postId)
                .user(user)
                .build();
    }
}
