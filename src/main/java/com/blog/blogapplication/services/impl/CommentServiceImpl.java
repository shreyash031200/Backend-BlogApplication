package com.blog.blogapplication.services.impl;

import com.blog.blogapplication.entities.Comment;
import com.blog.blogapplication.entities.Post;
import com.blog.blogapplication.exceptions.ResourceNotFoundException;
import com.blog.blogapplication.payloads.CommentDto;
import com.blog.blogapplication.respositories.CommentRepo;
import com.blog.blogapplication.respositories.PostRepo;
import com.blog.blogapplication.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepo postRepo;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post", "postId", postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment save = this.commentRepo.save(comment);
        return this.modelMapper.map(save,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer id) {
        Comment comment = this.commentRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Comment", " Comment ID", id));
        this.commentRepo.delete(comment);
    }
}
