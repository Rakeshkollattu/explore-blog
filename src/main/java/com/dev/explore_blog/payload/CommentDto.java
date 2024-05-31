package com.dev.explore_blog.payload;

import lombok.*;

@Data
public class CommentDto {

    private Long id;
    private String name;
    private String email;
    private String body;

}
