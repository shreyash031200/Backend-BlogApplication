package com.blog.blogapplication.respositories;

import com.blog.blogapplication.entities.Category;
import com.blog.blogapplication.entities.Post;
import com.blog.blogapplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);


    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String keyword);
}
