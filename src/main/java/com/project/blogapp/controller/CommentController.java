package com.project.blogapp.controller;

import com.project.blogapp.payload.CommentDto;
import com.project.blogapp.service.CommentService;
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


    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);

    }

    @GetMapping("/post/{postId}/comments")
    public List<CommentDto> getCommentByPostId(@PathVariable(value = "postId") long postId){
        return commentService.getCommentsByPostId(postId);
    }
    @GetMapping("/post/{postId}/comments/{id}")
    public CommentDto getCommentByCommentId(@PathVariable(value = "postId") long postId, @PathVariable(value = "id") long id){
        return commentService.getCommentById(postId,id);
    }

    @PutMapping("/post/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateCommentById(@PathVariable(value = "postId") long postId, @PathVariable(value = "id") long id ,
                                        @RequestBody CommentDto commentDto){
        CommentDto commentDto1 =  commentService.updateCommentById(postId,id,commentDto);

        return new ResponseEntity<>(commentDto1,HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}/comments/{id}")
    public ResponseEntity<String> deleteCommentId(@PathVariable(value = "postId") long postId, @PathVariable(value = "id") long id ){
       commentService.deleteCommentById(postId,id);
       String message = ("Comment deleted successfully of post id :" + postId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

}
