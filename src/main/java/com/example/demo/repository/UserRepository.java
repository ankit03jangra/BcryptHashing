package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByusername(String username);

    @Query(value = "SELECT * FROM USERS WHERE USERS.USERNAME LIKE ?1%", nativeQuery = true)
    List<User> findByUserNameStartsWith(String initial);

}
