package com.blog.blogapplication.services;

import com.blog.blogapplication.entities.Post;
import com.blog.blogapplication.payloads.PostDto;
import com.blog.blogapplication.payloads.PostResponse;

import java.util.List;

public interface PostService {

    //create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    // update
    PostDto updatePost(PostDto postDto, Integer postId);
    //delete
    void deletePost(Integer postId);
    //Getallppost
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    // get single post
    PostDto getPostById(Integer postId);

    // Get all post by Category;
    List<PostDto> getPostByCategory(Integer categoryId);
    //Get all post by User
    List<PostDto> getPostByUser(Integer userId );

    List<PostDto> searchPosts(String keyword);


}
