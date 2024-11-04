package com.example.blogBipin.services.impl;

import com.example.blogBipin.Dao.PostDao;
import com.example.blogBipin.Dto.PostDto;
import com.example.blogBipin.entity.Post;
import com.example.blogBipin.entity.User;
import com.example.blogBipin.exceptions.PostNotFoundException;
import com.example.blogBipin.mapper.EntityMapper;
import com.example.blogBipin.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private EntityMapper entityMapper;

    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    @Override
    public PostDto createPost(PostDto postDto, Long authorId) {
        Post post = entityMapper.toPostEntity(postDto);
        // Set the author for the post (you may want to fetch the User object by authorId)
        // post.setAuthor(user);
        post = postDao.save(post);
        logger.info("Post created with ID: {}", post.getId());
        return entityMapper.toPostDto(post);
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postDao.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found with ID: " + id));
        return entityMapper.toPostDto(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postDao.findAll();
        return entityMapper.toPostDtoList(posts);
    }

    @Override
    public PostDto updatePost(Long id, PostDto postDto) {
        Post existingPost = postDao.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found with ID: " + id));
        existingPost.setTitle(postDto.getTitle());
        existingPost.setContent(postDto.getContent());
        postDao.save(existingPost);

        logger.info("Post updated with ID: {}", existingPost.getId());
        return entityMapper.toPostDto(existingPost);
    }

    @Override
    public void deletePost(Long id) {
        postDao.deleteById(id);
        logger.info("Post deleted with ID: {}", id);
    }
}
