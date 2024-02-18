package com.project.blogapp.service.impl;

import com.project.blogapp.entity.Post;
import com.project.blogapp.exception.ResourceNotFoundException;
import com.project.blogapp.payload.PostDto;
import com.project.blogapp.payload.PostResponse;
import com.project.blogapp.repository.PostRepo;
import com.project.blogapp.service.PostService;
import com.project.blogapp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    CommonUtils commonUtils;


    private PostRepo postRepo;
    public PostServiceImpl(PostRepo postRepo) {
        this.postRepo = postRepo;
    }



    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = commonUtils.mapToEntity(postDto);
        Post response = postRepo.save(post);
        return commonUtils.mapToDto(response);
    }

    @Override
    public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);
        Page<Post> posts = postRepo.findAll(pageable);
        List<Post> postList = posts.getContent();
        List<PostDto> content =  postList.stream().map(commonUtils::mapToDto).toList();

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalPage(posts.getTotalPages());
        postResponse.setTotalElement(posts.getTotalElements());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Long id) {
       return commonUtils.mapToDto(postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id", id)));
    }

    @Override
    public PostDto updatePostById(PostDto postDto, Long id) {
        Post post  = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post" ,"id",id));
        post.setContent(post.getContent());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());

        return commonUtils.mapToDto(postRepo.save(post));
    }

    @Override
    public void deleteById(Long id) {
        if(postRepo.findById(id).isEmpty()){
            throw new ResourceNotFoundException("Post","id :",id);
        }
       postRepo.deleteById(id);
    }


}
