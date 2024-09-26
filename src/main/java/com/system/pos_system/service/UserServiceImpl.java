package com.system.pos_system.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.system.pos_system.entity.User;
import com.system.pos_system.repository.UserRepository;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

}
