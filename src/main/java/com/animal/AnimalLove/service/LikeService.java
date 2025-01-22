package com.animal.AnimalLove.service;

import com.animal.AnimalLove.data.dto.LikeDto;
import com.animal.AnimalLove.data.dto.LikeResponseDto;
import com.animal.AnimalLove.data.dto.PostDto;
import com.animal.AnimalLove.data.entity.Like;
import com.animal.AnimalLove.data.entity.Post;
import com.animal.AnimalLove.data.entity.User;
import com.animal.AnimalLove.data.repository.LikeRepository;
import com.animal.AnimalLove.data.repository.PostRepository;
import com.animal.AnimalLove.data.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public Boolean likeYN(String email, Long postId) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found in like"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found in like"));

        // 이미 liked되있으면 삭제
        Optional<Like> likeYN = likeRepository.findFirstByPostAndUser(post,user);
        if(likeYN.isPresent()) {
            likeRepository.deleteById(likeYN.get().getLikeId());
            return false;
        }else{
            likeRepository.save(LikeDto.toEntity(post, user));
            return true;
        }

    }

    public Boolean likeCheck(String email, Long postId) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found in like"));

        return likeRepository.findFirstByPostAndUser(post,user).isPresent();
    }


    public int likeCount(Long postId) {
        return likeRepository.countByPostId(postId);
    }

    public List<String> likeWho(String email, Long postId) {
        return likeRepository.findNamesByPostId(postId);
    }

    public List<LikeResponseDto> likeList(Long userId) {
        List<Like> likeList = likeRepository.findAllByUserId(userId);

    return likeList.stream().map(LikeResponseDto::from).toList();
    }
}
