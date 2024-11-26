package com.animal.AnimalLove.data.dto;

import com.animal.AnimalLove.data.entity.User;

public record UserDto(
        Long userId,
        String username,
        String name,
        String email,
        String role,
        byte[] profileImage
) {

    public static UserDto of(Long userId,String username,String name, String email, String role, byte[] profileImage) {
        return new UserDto(userId,username, name, email, role, profileImage);
    }

    public static UserDto ofJwt(String username,String role) {
        return new UserDto(null,username,null,null,role,null);
    }

    public static UserDto from(User user){
        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getProfileImage()
        );
    }

    public User toEntity(){
        return User.builder()
                .username(username)
                .name(name)
                .email(email)
                .role(role)
                .build();
    }

}
