package com.animal.AnimalLove.data.dto;

import com.animal.AnimalLove.data.entity.Post;
import com.animal.AnimalLove.data.entity.User;

public record UserDto(
        Long userId,
        String username,
        String email,
        String role,
        byte[] profileImage
) {

    public static UserDto of(Long userId, String username, String email, String role, byte[] profileImage) {
        return new UserDto(userId, username, email, role, profileImage);
    }

    public static UserDto from(User user){
        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getProfileImage()
        );
    }

    public User toEntity(){
        return User.builder()
                .userId(userId)
                .username(username)
                .email(email)
                .role(role)
                .build();
    }

}
