package com.dev.explore_blog.service;

import com.dev.explore_blog.payload.PostDto;
import com.dev.explore_blog.payload.PostResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int size, String sortBy, String sortDir);

    PostDto getPost(Long id);

    PostDto updatePost(PostDto postDto, Long id);

    void deletePost(Long id);
}

