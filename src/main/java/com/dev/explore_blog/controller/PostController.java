package com.dev.explore_blog.controller;

import com.dev.explore_blog.payload.PostDto;
import com.dev.explore_blog.payload.PostResponse;
import com.dev.explore_blog.service.PostService;
import com.dev.explore_blog.utils.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public PostResponse getAllPosts(@RequestParam(value = "pageNo",defaultValue = Constants.PAGE_NO,required = false) int pageNo,
                                    @RequestParam(value = "size",defaultValue =Constants.PAGE_SIZE,required = false) int size,
                                    @RequestParam(value = "sortBy",defaultValue = Constants.SORT_BY,required = false) String sortBy,
                                    @RequestParam(value = "sortDir", defaultValue = Constants.SORT_DIR, required = false) String sortDir){
        return postService.getAllPosts(pageNo,size,sortBy,sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto,
                                                  @PathVariable("id") Long id) {
        PostDto response = postService.updatePost(postDto,id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> updatePostById(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("Entity deleted successfully", HttpStatus.OK);
    }
}
