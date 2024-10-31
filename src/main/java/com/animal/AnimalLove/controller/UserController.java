package com.animal.AnimalLove.controller;

import com.animal.AnimalLove.constants.ApiUrlConstants;
import com.animal.AnimalLove.data.dto.PostDto;
import com.animal.AnimalLove.data.dto.UserDto;
import com.animal.AnimalLove.service.PostService;
import com.animal.AnimalLove.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저 등록", description = "새로운 유저를 등록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시물이 성공적으로 등록되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.")
    })
    @PostMapping(ApiUrlConstants.API_V1_POST_REGISTER)
    public ResponseEntity<UserDto> userRegister(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.registerUser(userDto);
        return ResponseEntity.ok().body(savedUser);
    }

}
