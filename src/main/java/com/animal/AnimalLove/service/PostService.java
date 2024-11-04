package com.animal.AnimalLove.service;

import com.animal.AnimalLove.data.dto.PostDto;
import com.animal.AnimalLove.data.entity.Posts;
import com.animal.AnimalLove.data.entity.Users;
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

    public Long registerPost(PostDto postDto){

        // 임의 user
        MockUserUtil userUtil = new MockUserUtil();
        Users users = userUtil.getMockUser();

        Users user = userRepository.findById(users.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        log.info("[userRepository.findById] 값 : {}, {}",user.getUserId(), user.getUsername());

        Posts post = postDto.toEntityWithUser(user);

        Posts savedPost = postRepository.save(post);
        return savedPost.getPostId();

    }
}
