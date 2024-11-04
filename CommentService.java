package com.example.blogBipin.services;

import com.example.blogBipin.Dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Long postId, Long authorId);
    CommentDto getCommentById(Long id);
    List<CommentDto> getCommentsByPostId(Long postId);
    CommentDto updateComment(Long id, CommentDto commentDto);
    void deleteComment(Long id);
}
