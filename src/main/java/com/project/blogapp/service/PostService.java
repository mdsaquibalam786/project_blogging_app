package com.project.blogapp.service;

import com.project.blogapp.payload.PostDto;
import com.project.blogapp.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePostById(PostDto postDto, Long id);

    void deleteById(Long id);
}
