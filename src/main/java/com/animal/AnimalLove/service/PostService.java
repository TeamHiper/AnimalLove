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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
                        .of(url,publicId,savedPost.getPostId())
                        .toEntity(savedPost));
        return savedPost.getPostId();

    }

    // 게시물 조회
    public PostDto getPost(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));

        log.info("[getPost] postDto: {}", PostDto.from(post));


        return PostDto.from(post);
    }

    // 게시물 리스트 조회
    public List<PostDto> getPostList(int page, int size){

        //page 0 아니면 -1
        if(page !=0){
            page = page -1;
        }

        // Pageable 객체 생성
        Pageable pageable = PageRequest.of(page, size);

        Page<Post> postList = postRepository.findAllWithImages(pageable);

        return postList.getContent().stream().map(PostDto :: from)
                .collect(Collectors.toList());
    }


    // 게시물 수정
    @Transactional
    public int updatePost(PostDto postDto, String url, String publicId){

        // postDto의 user email에 해당하는 user 정보를 조회
        String userEmail = postDto.user().email();
        User user = userRepository.findByEmail(userEmail).
                orElseThrow(() -> new IllegalArgumentException(userEmail + " 로 등록된 사용자를 찾을 수 없습니다."));

        // postDto의 postId를 통해 post 정보를 조회
        Long postId = postDto.postId();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));

        // 위에서 조회한 두 Entity에서 user email을 대조해본다.
        if (!post.getUser().getEmail().equals(user.getEmail())) {
            throw new SecurityException("게시물 수정 권한이 없습니다.");
        }

        // 더티 체킹으로 update 진행하기 위해 Entity에 직접 수정 작업
        post.updateContent(postDto.content());

//        int postUpdateResult = postRepository.updatePost(
//                postDto.postId(),
//                postDto.content(),
//                user
//        );

        // Image 수정
        List<Image> images = post.getImages();
        if (images.isEmpty()){
            throw new IllegalArgumentException("해당 게시물에 연결된 이미지를 찾을 수 없습니다.");
        }

        int imageUpdateResult = 1; // 업데이트 성공 여부 (1: 성공, 0: 실패)

        for (Image image : post.getImages()) {
            int updateResult = imageRepository.updateImage(
                    image.getId(),
                    url,
                    publicId,
                    post
            );

            if (updateResult == 0) imageUpdateResult = updateResult; // 하나라도 실패하면 최종 결과를 실패로 설정
        }

        // 두 업데이트 결과를 조합하여 반환 (성공 시 1, 실패 시 0)
        return imageUpdateResult;
//        return (postUpdateResult > 0 && imageUpdateResult > 0) ? 1 : 0;

    }
}
