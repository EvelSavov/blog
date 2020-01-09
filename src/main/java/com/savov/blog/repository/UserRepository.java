package com.savov.blog.repository;

import com.savov.blog.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findByUsername(String username);

    void deleteByUsername(String username);
}
