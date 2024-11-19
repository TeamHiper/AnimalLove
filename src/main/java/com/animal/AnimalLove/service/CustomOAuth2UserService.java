package com.animal.AnimalLove.service;

import com.animal.AnimalLove.data.dto.*;
import com.animal.AnimalLove.data.entity.User;
import com.animal.AnimalLove.data.repository.UserRepository;
import com.animal.AnimalLove.data.response.GoogleResponse;
import com.animal.AnimalLove.data.response.NaverResponse;
import com.animal.AnimalLove.data.response.OAuth2Response;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if(registrationId.equals("naver")){

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());

        } else if (registrationId.equals("google")){

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());

        } else {
            return null;
        }

        // 로그인 성공 시 로직 추후 작성
        String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        User existData = userRepository.findByUsername(username);

        if (existData == null){

            UserDto newUserDto = UserDto.of(username, oAuth2Response.getEmail(), oAuth2Response.getName(), "ROLE_USER",null);
            userRepository.save(newUserDto.toEntity());

            return new CustomOAuth2User(newUserDto);

        } else {
            UserDto userDto = UserDto.from(existData);
            UserDto newUserDto = UserDto.of(userDto.username(),
                    oAuth2Response.getEmail(),
                    oAuth2Response.getName(),
                    userDto.role(),
                    userDto.profileImage()
                    );

            userRepository.save(newUserDto.toEntity());

            return new CustomOAuth2User(newUserDto);


        }





    }
}
