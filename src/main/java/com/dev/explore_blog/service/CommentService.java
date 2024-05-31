package com.dev.explore_blog.service;

import com.dev.explore_blog.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(Long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(Long postId);

    CommentDto getCommentsByCommentId(Long commentId,Long postId);

    CommentDto editByCommentId(Long commentId, Long postId, CommentDto editedComment);

    void deleteByCommentId(Long commentId, Long postId);
}

