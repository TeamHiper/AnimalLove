package com.animal.AnimalLove.util;

import com.animal.AnimalLove.data.dto.UserDto;
import com.animal.AnimalLove.data.entity.User;

public class MockUserUtil {

    private static final User mockUser = User.builder()
            .userId(1L)
            .username("테스트")
            .email("test@naver.com")
            .role("ADMIN")
            .build();

    public static User getMockUser() {
        return mockUser;
    }

}
