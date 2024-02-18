package com.project.blogapp.controller;

import com.project.blogapp.payload.PostDto;
import com.project.blogapp.payload.PostResponse;
import com.project.blogapp.service.PostService;
import com.project.blogapp.util.ApplicationConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
       return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }


    @GetMapping
    public PostResponse getAllPosts(@RequestParam(name = "pageNo", defaultValue = ApplicationConstants.DEFAULT_PAGE_NO,required = false) int pageNo,
                                    @RequestParam(name = "pageSize", defaultValue = ApplicationConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
                                    @RequestParam(name = "sortBy", defaultValue = ApplicationConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                    @RequestParam(name = "sortDir", defaultValue = ApplicationConstants.DEFAULT_SORT_DIR,required = false) String sortDir){
        return postService.getAllPost(pageNo,pageSize,sortBy,sortDir);
    }

    @GetMapping("/{id}")
    public PostDto getPostById(@PathVariable(name = "id") Long id){
        return postService.getPostById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable(name = "id") Long id){
        PostDto postDto1 = postService.updatePostById(postDto,id);
        return new ResponseEntity<>(postDto1,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id){
        postService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
