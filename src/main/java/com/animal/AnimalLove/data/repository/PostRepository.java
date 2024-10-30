package com.animal.AnimalLove.data.repository;

import com.animal.AnimalLove.data.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Posts, Long> {
}
