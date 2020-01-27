package com.savov.blog.repository;

import com.savov.blog.domain.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    List<Post> findByUserId(Long id);

    List<Post> findByCategoryId(Long id);

    List<Post> findTop5ByOrderByLikeCountDesc();

}
