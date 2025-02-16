package com.animal.AnimalLove.data.dto;

import com.animal.AnimalLove.data.entity.Image;
import com.animal.AnimalLove.data.entity.User;

public record UserDto(
        String provider,
        String nickname,
        String name,
        String email,
        String role,
        String profileImageUrl
) {

    public static UserDto of(String provider,String nickname,String name, String email, String role, String profileImageUrl) {
        return new UserDto(provider,nickname, name, email, role, profileImageUrl);
    }

    public static UserDto ofJwt(String email,String role) {
        return new UserDto(null,null,null,email,role,null);
    }

    public static UserDto from(User user){
        return new UserDto(
                user.getProvider(),
                user.getNickname(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getProfileImageUrl()
        );
    }

    public User toEntity(){
        return User.builder()
                .provider(provider)
                .nickname(nickname)
                .name(name)
                .email(email)
                .role(role)
                .profileImageUrl(profileImageUrl)
                .build();
    }

}
