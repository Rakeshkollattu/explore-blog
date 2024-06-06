package com.dev.explore_blog.service.impl;

import com.dev.explore_blog.entity.Category;
import com.dev.explore_blog.entity.Post;
import com.dev.explore_blog.exception.ResourceNotFoundException;
import com.dev.explore_blog.payload.PostDto;
import com.dev.explore_blog.payload.PostResponse;
import com.dev.explore_blog.repository.CategoryRepository;
import com.dev.explore_blog.repository.PostRepository;
import com.dev.explore_blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper mapper;
    private final CategoryRepository categoryRepository;

    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("category", "id", postDto.getId()));

        Post post = getPost(postDto);
        post.setCategory(category);

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
        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("category", "id", postDto.getId()));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setCategory(category);

        Post updatedPost = postRepository.save(post);

        return getPostDto(updatedPost);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        postRepository.delete(post);
    }

    @Override
    public List<PostDto> getPostByCategory(Long id) {

        //check if category is there or not
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("category", "id", id));

        List<Post> postsList = postRepository.findByCategoryId(id);

        return postsList.stream().map((post) -> mapper.map(post, PostDto.class)).toList();
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
