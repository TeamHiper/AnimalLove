package com.animal.AnimalLove.data.repository.support;

import com.animal.AnimalLove.data.entity.User;

public interface CustomPostRepository {
    int updatePost(Long postId, String content, User user);
}
