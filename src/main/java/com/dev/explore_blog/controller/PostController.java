package com.dev.explore_blog.controller;

import com.dev.explore_blog.payload.PostDto;
import com.dev.explore_blog.payload.PostResponse;
import com.dev.explore_blog.service.PostService;
import com.dev.explore_blog.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("exploreBlog/post")
@Tag(
        name = "Rest APIs for Posts"
)
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "retrieves all posts based on category id", description = "used to retrieve all of the posts from the database based on the category")
    @ApiResponse(responseCode = "200",description = "HTTP Status 200 OK")
    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> getAllPostByCategory(@PathVariable("id") Long id){
        List<PostDto> response = postService.getPostByCategory(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "creates a new post", description = "used to save new posts into the database")
    @ApiResponse(responseCode = "201",description = "HTTP Status 201 CREATED")
    @SecurityRequirement(name="Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @Operation(summary = "all posts", description = "used to retrieve all of the posts from database")
    @ApiResponse(responseCode = "200",description = "HTTP Status 200 OK")
    @GetMapping("/all")
    public PostResponse getAllPosts(@RequestParam(value = "pageNo",defaultValue = Constants.PAGE_NO,required = false) int pageNo,
                                    @RequestParam(value = "size",defaultValue =Constants.PAGE_SIZE,required = false) int size,
                                    @RequestParam(value = "sortBy",defaultValue = Constants.SORT_BY,required = false) String sortBy,
                                    @RequestParam(value = "sortDir", defaultValue = Constants.SORT_DIR, required = false) String sortDir){
        return postService.getAllPosts(pageNo,size,sortBy,sortDir);
    }

    @Operation(summary = "individual post using id", description = "used to retrieve individual posts from the database using id")
    @ApiResponse(responseCode = "201",description = "HTTP Status 201 CREATED")
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @Operation(summary = "updates an existing post", description = "used to update an existing post in the database using id")
    @ApiResponse(responseCode = "201",description = "HTTP Status 201 CREATED")
    @SecurityRequirement(name="Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<PostDto> updatePostById(@Valid @RequestBody PostDto postDto,
                                                  @PathVariable("id") Long id) {
        PostDto response = postService.updatePost(postDto,id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "deletes an existing post using id", description = "used to delete an existing post from the database using id")
    @ApiResponse(responseCode = "200",description = "HTTP Status 200 OK")
    @SecurityRequirement(name="Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("Entity deleted successfully", HttpStatus.OK);
    }

}
