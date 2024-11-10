package com.animal.AnimalLove.util;

import com.animal.AnimalLove.data.entity.User;
import lombok.Getter;

public class MockUserUtil {

    @Getter
    private static final User mockUser = User.builder()
            .userId(1L)
            .username("테스트")
            .email("test@naver.com")
            .role("ADMIN")
            .build();

}
