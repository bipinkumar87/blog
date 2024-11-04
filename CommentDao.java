package com.example.blogBipin.Dao;

import com.example.blogBipin.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentDao extends JpaRepository<Comment, Long> {
    List<Comment> findByPost_Id(Long postId);
}
