package com.animal.AnimalLove.service;

import com.animal.AnimalLove.data.dto.LikeDto;
import com.animal.AnimalLove.data.entity.Like;
import com.animal.AnimalLove.data.entity.User;
import com.animal.AnimalLove.data.repository.LikeRepository;
import com.animal.AnimalLove.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    public Long likeRegister(Long userId, Long postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        likeRepository.save(LikeDto.toEntity(postId,user));
        return postId;
    }

    public Long likeDelete(Long likeId) {
               Like like = likeRepository.findById(likeId)
                       .orElseThrow(() -> new RuntimeException("Like not found"));
               likeRepository.delete(like);
        return likeId;
    }
}
