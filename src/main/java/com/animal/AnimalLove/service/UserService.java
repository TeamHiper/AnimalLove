package com.animal.AnimalLove.service;

import com.animal.AnimalLove.data.dto.UserDto;
import com.animal.AnimalLove.data.entity.Users;
import com.animal.AnimalLove.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserDto registerUser(UserDto userDto){

        Users user = userDto.toEntity();
        Users savedUser = userRepository.save(user);
        return UserDto.from(savedUser);

    }
}
