package com.animal.AnimalLove.service;

import com.animal.AnimalLove.data.dto.ImageDto;
import com.animal.AnimalLove.data.dto.PostDto;
import com.animal.AnimalLove.data.entity.Image;
import com.animal.AnimalLove.data.entity.Post;

import com.animal.AnimalLove.data.entity.User;

import com.animal.AnimalLove.data.repository.ImageRepository;
import com.animal.AnimalLove.data.repository.PostRepository;
import com.animal.AnimalLove.data.repository.UserRepository;
import com.animal.AnimalLove.util.MockUserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    public Long registerPost(PostDto postDto, String url, String publicId){

        // 임의 user
        MockUserUtil userUtil = new MockUserUtil();
        User users = userUtil.getMockUser();

        User user = userRepository.findById(users.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        log.info("[userRepository.findById] 값 : {}, {}",user.getUserId(), user.getUsername());

        Post post = postDto.toEntityWithUser(user);

        Post savedPost = postRepository.save(post);
        //이미지 저장
        imageRepository.save(
                        ImageDto
                        .of(url,publicId,savedPost)
                        .toEntity());
        return savedPost.getPostId();

    }
}
