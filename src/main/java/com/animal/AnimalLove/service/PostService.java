package com.animal.AnimalLove.service;

import com.animal.AnimalLove.data.dto.PostDto;
import com.animal.AnimalLove.data.entity.Posts;
import com.animal.AnimalLove.data.entity.Users;
import com.animal.AnimalLove.data.repository.PostRepository;
import com.animal.AnimalLove.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostDto registerPost(PostDto postDto){
//        Users user = userRepository.findById(postDto.user().getUserId())
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Users user = postDto.toUser();
        Users savedUser = userRepository.save(user);

        Posts post = postDto.toEntity();
        Posts savedPost = postRepository.save(post);
        return PostDto.from(savedPost);

    }
}
