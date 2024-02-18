package com.project.blogapp.repository;

import com.project.blogapp.entity.Comment;
import com.project.blogapp.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Long> {

    List<Comment> findByPostId(long postId);
}
