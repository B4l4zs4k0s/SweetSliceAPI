package com.cake.sweetsliceapi.user.repository;

import com.cake.sweetsliceapi.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
