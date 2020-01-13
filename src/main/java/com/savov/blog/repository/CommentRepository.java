package com.savov.blog.repository;

import com.savov.blog.domain.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment>  findByPostId(Long postId);

    List<Comment> findByUserId(Long userId);

    Comment findByPostIdAndId(Long postId, Long id);

}
