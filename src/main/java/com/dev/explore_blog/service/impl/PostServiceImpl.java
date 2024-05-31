package com.dev.explore_blog.service.impl;

import com.dev.explore_blog.entity.Comment;
import com.dev.explore_blog.entity.Post;
import com.dev.explore_blog.exception.ResourceNotFoundException;
import com.dev.explore_blog.payload.CommentDto;
import com.dev.explore_blog.payload.PostDto;
import com.dev.explore_blog.payload.PostResponse;
import com.dev.explore_blog.repository.CommentRepository;
import com.dev.explore_blog.repository.PostRepository;
import com.dev.explore_blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = getPost(postDto);
        Post newPost = postRepository.save(post);
        return getPostDto(newPost);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int size, String sortBy, String sortDir) {
        return getPostResponse(pageNo, size, sortBy, sortDir);
    }

    @Override
    public PostDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        return getPostDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);

        return getPostDto(updatedPost);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        postRepository.delete(post);
    }

    // Entity to Dto
    private PostDto getPostDto(Post newPost) {
        return mapper.map(newPost, PostDto.class);
    }

    // Dto to Entity
    private Post getPost(PostDto postDto) {
        return mapper.map(postDto, Post.class);
    }

    // List to Response
    private PostResponse getPostResponse(int pageNo, int size, String sortBy, String sortDir) {

        // making pageable
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, size, sort);
        Page<Post> postsPageable = postRepository.findAll(pageable);

        //getting content
        List<Post> postsList = postsPageable.getContent();
        List<PostDto> content = postsList.stream().map(this::getPostDto).toList();
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(postsPageable.getNumber());
        postResponse.setPageSize(postsPageable.getSize());
        postResponse.setTotalElements(postsPageable.getTotalElements());
        postResponse.setTotalPages(postsPageable.getTotalPages());
        postResponse.setLast(postsPageable.isLast());

        return postResponse;
    }

}
