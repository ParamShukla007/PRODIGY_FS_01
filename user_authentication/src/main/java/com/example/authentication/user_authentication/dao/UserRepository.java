package com.example.authentication.user_authentication.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.authentication.user_authentication.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Integer>{
    @Query("select u from User u where u.email = :email")
    public User getUserByUserEmail(@Param("email") String email);
}

