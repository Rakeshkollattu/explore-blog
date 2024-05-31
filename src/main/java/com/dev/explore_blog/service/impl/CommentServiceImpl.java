package com.dev.explore_blog.service.impl;

import com.dev.explore_blog.entity.Comment;
import com.dev.explore_blog.entity.Post;
import com.dev.explore_blog.exception.ExploreApiException;
import com.dev.explore_blog.exception.ResourceNotFoundException;
import com.dev.explore_blog.payload.CommentDto;
import com.dev.explore_blog.repository.CommentRepository;
import com.dev.explore_blog.repository.PostRepository;
import com.dev.explore_blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    private final ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {

//        Comment comment = getComment(commentDto);
        Comment comment = mapper.map(commentDto,Comment.class);
        // getting post for adding comment
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        // connecting post to comment entity
        comment.setPost(post);
        // saving
        Comment newComment = commentRepository.save(comment);

        return mapper.map(newComment,CommentDto.class);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        Set<Comment> comments = commentRepository.findAllByPostId(postId);
        return comments.stream().map(comment ->mapper.map(comment,CommentDto.class)).toList();
    }

    @Override
    public CommentDto getCommentsByCommentId(Long commentId, Long postId) {

        //post from db
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));

        // comment from db
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new ExploreApiException(HttpStatus.BAD_REQUEST, "comment doesn't belongs to the post");
        }
        return mapper.map(comment,CommentDto.class);
    }

    @Override
    public CommentDto editByCommentId(Long commentId, Long postId, CommentDto editedComment) {

        //post from db
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));

        // comment from db
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new ExploreApiException(HttpStatus.BAD_REQUEST, "comment doesn't belongs to the post");
        }

        comment.setName(editedComment.getName());
        comment.setEmail(editedComment.getEmail());
        comment.setBody(editedComment.getBody());

        //save
        Comment updatedComment  = commentRepository.save(comment);
        return mapper.map(updatedComment,CommentDto.class);
    }

    @Override
    public void deleteByCommentId(Long commentId, Long postId) {

        //post from db
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));

        // comment from db
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new ExploreApiException(HttpStatus.BAD_REQUEST, "comment doesn't belongs to the post");
        }

        commentRepository.delete(comment);
    }

//    private Comment getComment(CommentDto commentDto) {
//        Comment comment = new Comment();
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
//        return comment;
//    }

//    private CommentDto getCommentDto(Comment comment) {
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
//        return commentDto;
//    }
}
