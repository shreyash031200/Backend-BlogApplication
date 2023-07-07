package com.blog.blogapplication.services;

import com.blog.blogapplication.payloads.CommentDto;

public interface CommentService {

    // create Comment
    public CommentDto createComment(CommentDto commentDto, Integer postId);

    // Delete Comment
    public void deleteComment(Integer id);
}
