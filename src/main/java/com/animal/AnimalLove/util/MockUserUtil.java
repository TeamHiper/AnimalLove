package com.animal.AnimalLove.util;

import com.animal.AnimalLove.data.dto.UserDto;
import com.animal.AnimalLove.data.entity.Users;

public class MockUserUtil {

    private static final Users mockUser = Users.builder()
            .userId(1L)
            .username("테스트")
            .email("test@naver.com")
            .role("ADMIN")
            .build();

    public static Users getMockUser() {
        return mockUser;
    }

}
