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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

//        User user = userRepository.findById(users)
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));

//        log.info("[userRepository.findById] 값 : {}, {}",user.getUserId(), user.getUsername());

        Post post = postDto.toEntityWithUser(users);

        Post savedPost = postRepository.save(post);
        //이미지 저장
        imageRepository.save(
                        ImageDto
                        .of(url,publicId,savedPost)
                        .toEntity());
        return savedPost.getPostId();

    }

    public PostDto getPost(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        log.info("[getPost] postDto: {}", PostDto.from(post));


        return PostDto.from(post);
    }

    public List<PostDto> getPostList(int page, int size){
        // Pageable 객체 생성
        Pageable pageable = PageRequest.of(page, size);

        Page<Post> postList = postRepository.findAll(pageable);

        return postList.getContent().stream().map(PostDto :: from)
                .collect(Collectors.toList());
    }

}
