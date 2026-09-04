package com.bike.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bike.security.model.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {


    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
    User findByEmail(String email);

}
