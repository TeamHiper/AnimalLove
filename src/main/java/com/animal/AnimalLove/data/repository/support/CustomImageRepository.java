package com.animal.AnimalLove.data.repository.support;

import com.animal.AnimalLove.data.entity.Post;

public interface CustomImageRepository {
    int updateImage(Long id, String url, String publicId, Post post);
}
