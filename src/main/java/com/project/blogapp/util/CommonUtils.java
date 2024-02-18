package com.project.blogapp.util;

import com.project.blogapp.config.Mapper;
import com.project.blogapp.entity.Comment;
import com.project.blogapp.entity.Post;
import com.project.blogapp.payload.CommentDto;
import com.project.blogapp.payload.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonUtils {

    @Autowired
    Mapper mapper;
    public PostDto mapToDto(Post post){
        return mapper.modelMapper().map(post,PostDto.class);
    }

    public Post mapToEntity(PostDto postDto){
        return mapper.modelMapper().map(postDto,Post.class);
    }

    public CommentDto mapToCommentDto(Comment comment){
        return mapper.modelMapper().map(comment,CommentDto.class);
    }

    public Comment mapToComment(CommentDto commentDto){
        return mapper.modelMapper().map(commentDto,Comment.class);
    }


}
