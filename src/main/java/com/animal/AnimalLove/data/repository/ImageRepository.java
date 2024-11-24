package com.animal.AnimalLove.data.repository;

import com.animal.AnimalLove.data.entity.Image;
import com.animal.AnimalLove.data.repository.support.CustomImageRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long>, CustomImageRepository {
}
