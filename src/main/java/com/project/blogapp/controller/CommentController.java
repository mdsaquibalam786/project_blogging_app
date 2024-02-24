package com.project.blogapp.controller;

import com.project.blogapp.payload.CommentDto;
import com.project.blogapp.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {


    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                   @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);

    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentByPostId(@PathVariable(value = "postId") long postId){
        return commentService.getCommentsByPostId(postId);
    }
    @GetMapping("/posts/{postId}/comments/{id}")
    public CommentDto getCommentByCommentId(@PathVariable(value = "postId") long postId, @PathVariable(value = "id") long id){
        return commentService.getCommentById(postId,id);
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateCommentById(@PathVariable(value = "postId") long postId, @PathVariable(value = "id") long id ,
                                        @Valid @RequestBody CommentDto commentDto){
        CommentDto commentDto1 =  commentService.updateCommentById(postId,id,commentDto);

        return new ResponseEntity<>(commentDto1,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteCommentId(@PathVariable(value = "postId") long postId, @PathVariable(value = "id") long id ){
       commentService.deleteCommentById(postId,id);
       String message = ("Comment deleted successfully of post id :" + postId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

}
