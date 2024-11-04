package com.example.blogBipin.services;

import com.example.blogBipin.Dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Long authorId);
    PostDto getPostById(Long id);
    List<PostDto> getAllPosts();
    PostDto updatePost(Long id, PostDto postDto);
    void deletePost(Long id);
}
