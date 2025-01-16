package com.animal.AnimalLove.service;

import com.animal.AnimalLove.data.dto.LikeDto;
import com.animal.AnimalLove.data.entity.Like;
import com.animal.AnimalLove.data.entity.User;
import com.animal.AnimalLove.data.repository.LikeRepository;
import com.animal.AnimalLove.data.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    @Transactional
    public Boolean likeYN(String email, Long postId) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 이미 liked되있으면 삭제
        Optional<Like> likeYN = likeRepository.findFirstByPostIdAndUser(postId,user);
        if(likeYN.isPresent()) {
            likeRepository.deleteById(likeYN.get().getLikeId());
            return false;
        }else{
            likeRepository.save(LikeDto.toEntity(postId, user));
            return true;
        }

    }

    public Boolean likeCheck(String email, Long postId) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return likeRepository.findFirstByPostIdAndUser(postId,user).isPresent();
    }


}
