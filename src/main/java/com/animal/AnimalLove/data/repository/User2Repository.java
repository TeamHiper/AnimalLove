package com.animal.AnimalLove.data.repository;

import com.animal.AnimalLove.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface User2Repository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}