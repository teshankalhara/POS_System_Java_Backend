package com.system.pos_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.pos_system.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
