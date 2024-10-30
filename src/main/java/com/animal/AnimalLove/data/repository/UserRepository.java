package com.animal.AnimalLove.data.repository;

import com.animal.AnimalLove.data.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {}
