package com.dev.explore_blog.controller;

import com.dev.explore_blog.payload.CommentDto;
import com.dev.explore_blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add/{postId}")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId") Long postId,
                                                    @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/post/{postId}")
    public List<CommentDto> getCommentsByPost(@PathVariable("postId") Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/post/{postId}/comment/{commentId}")
    public ResponseEntity<CommentDto> getCommentsById(@PathVariable("commentId") Long commentId,
                                                      @PathVariable("postId") Long postId) {
        CommentDto commentDto = commentService.getCommentsByCommentId(commentId, postId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @PutMapping("update/post/{postId}/comment/{commentId}")
    public ResponseEntity<CommentDto> editCommentsById(@PathVariable("commentId") Long commentId,
                                                       @PathVariable("postId") Long postId,
                                                       @RequestBody CommentDto editedComment) {
        CommentDto commentDto = commentService.editByCommentId(commentId, postId, editedComment);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @DeleteMapping("delete/post/{postId}/comment/{commentId}")
    public ResponseEntity<String> deleteCommentsById(@PathVariable("commentId") Long commentId,
                                                       @PathVariable("postId") Long postId) {
        commentService.deleteByCommentId(commentId, postId);
        return new ResponseEntity<>("Comment deleted Successfully",HttpStatus.OK);
    }

}
