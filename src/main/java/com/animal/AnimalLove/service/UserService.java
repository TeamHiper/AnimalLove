package com.animal.AnimalLove.service;

import com.animal.AnimalLove.data.dto.UserDto;
import com.animal.AnimalLove.data.entity.User;
import com.animal.AnimalLove.data.repository.UserRepository;
import com.animal.AnimalLove.util.MockUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserDto registerMockUser(){
        MockUserUtil mockUserUtil = new MockUserUtil();
        User user = mockUserUtil.getMockUser();

        User savedUser = userRepository.save(user);
        return UserDto.from(savedUser);
    }


    public UserDto registerUser(UserDto userDto){

        User user = userDto.toEntity();
        User savedUser = userRepository.save(user);
        return UserDto.from(savedUser);

    }
}
