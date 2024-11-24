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

    // 게시물 등록
    public Long registerPost(PostDto postDto, String url, String publicId){

        // 임의 user
        MockUserUtil userUtil = new MockUserUtil();
        User users = userUtil.getMockUser();

        User user = userRepository.findById(users.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("등록된 사용자를 찾을 수 없습니다."));

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

    // 게시물 조회
    public PostDto getPost(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));

        log.info("[getPost] postDto: {}", PostDto.from(post));


        return PostDto.from(post);
    }

    // 게시물 수정
    public int updatePost(PostDto postDto, String url, String publicId){

        // postDto의 user email에 해당하는 user 정보를 조회
        String userEmail = postDto.user().email();
        log.info("userEmail: {}", userEmail);
        User user = userRepository.findByEmail(userEmail).
                orElseThrow(() -> new IllegalArgumentException(userEmail + " 로 등록된 사용자를 찾을 수 없습니다."));
        log.info("조회된 user: {}", user);

        // postDto의 postId를 통해 post 정보를 조회
        Long postId = postDto.postId();
        log.info("postId: {}", postId);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));
        log.info("조회된 post: {}", post);

        // 위에서 조회한 두 Entity에서 user email을 대조해본다.
        if (!post.getUser().getEmail().equals(user.getEmail())) {
            throw new SecurityException("게시물 수정 권한이 없습니다.");
        }

        return postRepository.updatePost(
                postDto.postId(),
                postDto.content(),
                postDto.image(),
                user
        );

    }
}
