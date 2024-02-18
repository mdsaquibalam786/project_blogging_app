package com.project.blogapp.payload;

import com.project.blogapp.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private long id;
    private String name;
    private String email;
    private String body;

}
