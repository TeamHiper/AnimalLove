package com.animal.AnimalLove.data.repository;

import com.animal.AnimalLove.data.entity.Post;
import com.animal.AnimalLove.data.repository.support.CustomPostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long>, CustomPostRepository {

    @Query("SELECT p FROM Post p JOIN FETCH p.images")
    Page<Post> findAllWithImages(Pageable pageable);

}
