package com.bike.security.service;

import org.springframework.stereotype.Service;
import com.bike.security.model.User;
import com.bike.security.repository.UserRepository;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createOrUpdateUser(String email,String name, String picture,String googleId, byte[] image) {

        User user = userRepository.findByEmail(email);
            if(user==null){
                User user1 = new User();
                user1.setEmail(email);
                user1.setFullName(name);
                user1.setPhone("123" );
                user1.setPasswordHash("321");
                user1.setProfileImage(picture);
                user1.setActualImage(image);
                user1.setProfileImageType("image/jpeg");
                return userRepository.save(user1);
                 }
            else
              return user;
    }
}

