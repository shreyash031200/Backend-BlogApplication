package com.blog.blogapplication.controllers;

import com.blog.blogapplication.entities.Comment;
import com.blog.blogapplication.payloads.ApiResponse;
import com.blog.blogapplication.payloads.CommentDto;
import com.blog.blogapplication.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @PostMapping("/create/{postId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId){
        CommentDto comment1 = this.commentService.createComment(comment, postId);
        return new ResponseEntity<>(comment1, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer id){
        this.commentService.deleteComment(id);
        return new ResponseEntity<>(new ApiResponse("Comment Successfuly Deleted", true),HttpStatus.OK);
    }
}
