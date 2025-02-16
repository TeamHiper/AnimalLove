package com.animal.AnimalLove.service;

import com.animal.AnimalLove.data.dto.UserDto;
import com.animal.AnimalLove.data.entity.User;
import com.animal.AnimalLove.data.repository.UserRepository;
import com.animal.AnimalLove.jwt.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    public UserDto registerUser(UserDto userDto){

        User user = userDto.toEntity();
        User savedUser = userRepository.save(user);
        return UserDto.from(savedUser);

    }

    public UserDto getUser(String email){

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return UserDto.from(user);
    }

    @Transactional
    public UserDto updateProfileImage(String email, String profileImageUrl) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다"));

        // 기존 엔티티의 profileImageUrl만 변경
        user.updateProfileImageUrl(profileImageUrl);

        return UserDto.from(user);
    }
}
