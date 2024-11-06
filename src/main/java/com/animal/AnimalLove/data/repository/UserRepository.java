package com.animal.AnimalLove.data.repository;

import com.animal.AnimalLove.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
