package com.animal.AnimalLove.data.repository;

import com.animal.AnimalLove.data.entity.Like;
import com.animal.AnimalLove.data.entity.Post;
import com.animal.AnimalLove.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findFirstByPostAndUser(Post post, User user);

    void deleteByPostAndUser(Post post, User user);

    @Query("SELECT COUNT(l) FROM Like l WHERE l.post.postId = :postId")
    int countByPostId(@Param("postId") Long postId);

    @Query("SELECT l.user.name FROM Like l WHERE l.post.postId = :postId")
    List<String> findNamesByPostId(@Param("postId") Long postId);

    @Query("SELECT l FROM Like l WHERE l.user.userId = :userId")
    List<Like> findAllByUserId(@Param("userId") Long userId);

}
