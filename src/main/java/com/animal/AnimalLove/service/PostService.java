package com.animal.AnimalLove.service;

import com.animal.AnimalLove.data.dto.PostDto;
import com.animal.AnimalLove.data.entity.Posts;
import com.animal.AnimalLove.data.entity.Users;
import com.animal.AnimalLove.data.repository.PostRepository;
import com.animal.AnimalLove.data.repository.UserRepository;
import com.animal.AnimalLove.data.request.PostCreationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Long registerPost(PostCreationRequest request){
        Users user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        log.info("[userRepository.findById] 값 : {}, {}",user.getUserId(), user.getUsername());

        PostDto postDto = request.getPostDto();
        Posts post = postDto.toEntityWithUser(user);

        Posts savedPost = postRepository.save(post);
//        return PostDto.from(savedPost);
        return savedPost.getPostId();

    }
}
