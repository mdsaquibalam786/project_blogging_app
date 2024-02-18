package com.project.blogapp.service.impl;

import com.project.blogapp.entity.Comment;
import com.project.blogapp.entity.Post;
import com.project.blogapp.exception.BlogAPIException;
import com.project.blogapp.exception.ResourceNotFoundException;
import com.project.blogapp.payload.CommentDto;
import com.project.blogapp.repository.CommentRepo;
import com.project.blogapp.repository.PostRepo;
import com.project.blogapp.service.CommentService;
import com.project.blogapp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommonUtils commonUtils;
    private CommentRepo commentRepo;

    private PostRepo postRepo;

    public CommentServiceImpl(CommentRepo commentRepo,PostRepo postRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = commonUtils.mapToComment(commentDto);
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id : ",postId));
        comment.setPost(post);
        return commonUtils.mapToCommentDto(commentRepo.save(comment));
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepo.findByPostId(postId);
        return comments.stream().map(commonUtils::mapToCommentDto).toList();
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {

        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id :",postId));

        Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment","id",commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comments not belongs to respective post");

        }
        return commonUtils.mapToCommentDto(comment);
    }

    @Override
    public CommentDto updateCommentById(long postId, long commentId, CommentDto commentDto) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id :",postId));
        Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment","id",commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comments not belongs to respective post");

        }

        comment.setBody(commentDto.getBody());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());

        Comment updatedComment = commentRepo.save(comment);

        return commonUtils.mapToCommentDto(updatedComment);
    }

    @Override
    public void deleteCommentById(long postId, long commentId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id :",postId));
        Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment","id",commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comments not belongs to respective post");

        }
        commentRepo.deleteById(commentId);
    }


}
