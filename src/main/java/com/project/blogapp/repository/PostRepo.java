package com.project.blogapp.repository;

import com.project.blogapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Long> {


}
