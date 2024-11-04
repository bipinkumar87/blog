package com.example.blogBipin.services.impl;

import com.example.blogBipin.Dao.CommentDao;
import com.example.blogBipin.Dto.CommentDto;
import com.example.blogBipin.entity.Comment;
import com.example.blogBipin.entity.Post;
import com.example.blogBipin.entity.User;
import com.example.blogBipin.exceptions.CommentNotFoundException;
import com.example.blogBipin.mapper.EntityMapper;
import com.example.blogBipin.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private EntityMapper entityMapper;

    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId, Long authorId) {
        Comment comment = entityMapper.toCommentEntity(commentDto);
        // Set post and author (you may want to fetch the Post and User objects by IDs)
        // comment.setPost(post);
        // comment.setAuthor(user);
        comment = commentDao.save(comment);
        logger.info("Comment created with ID: {}", comment.getId());
        return entityMapper.toCommentDto(comment);
    }

    @Override
    public CommentDto getCommentById(Long id) {
        Comment comment = commentDao.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment not found with ID: " + id));
        return entityMapper.toCommentDto(comment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        return entityMapper.toCommentDtoList(commentDao.findByPost_Id(postId));
    }

    @Override
    public CommentDto updateComment(Long id, CommentDto commentDto) {
        Comment existingComment = commentDao.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment not found with ID: " + id));
        existingComment.setContent(commentDto.getContent());
        commentDao.save(existingComment);

        logger.info("Comment updated with ID: {}", existingComment.getId());
        return entityMapper.toCommentDto(existingComment);
    }

    @Override
    public void deleteComment(Long id) {
        commentDao.deleteById(id);
        logger.info("Comment deleted with ID: {}", id);
    }
}
