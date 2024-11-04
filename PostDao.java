package com.example.blogBipin.Dao;

import com.example.blogBipin.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDao extends JpaRepository<Post, Long> {
}
