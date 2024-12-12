package com.animal.AnimalLove.data.repository;

import com.animal.AnimalLove.data.entity.Like;
import com.animal.AnimalLove.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findFirstByPostIdAndUser(Long postId, User user);
}
