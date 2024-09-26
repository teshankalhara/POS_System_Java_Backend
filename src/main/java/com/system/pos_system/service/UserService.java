package com.system.pos_system.service;

import org.springframework.stereotype.Service;

import com.system.pos_system.entity.User;

@Service
public interface UserService {
    User createUser(User user);
}
