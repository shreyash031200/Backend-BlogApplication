package com.blog.blogapplication.respositories;

import com.blog.blogapplication.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
