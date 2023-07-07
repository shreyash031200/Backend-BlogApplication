package com.blog.blogapplication.controllers;

import com.blog.blogapplication.config.AppConstants;
import com.blog.blogapplication.entities.Post;
import com.blog.blogapplication.payloads.ApiResponse;
import com.blog.blogapplication.payloads.ImageResponse;
import com.blog.blogapplication.payloads.PostDto;
import com.blog.blogapplication.payloads.PostResponse;
import com.blog.blogapplication.services.FileService;
import com.blog.blogapplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;
    
    //  create post
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId, @PathVariable Integer categoryId){
        PostDto postDto1 = this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<>(postDto1, HttpStatus.CREATED);
    }
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
        List<PostDto> postByUser = this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(postByUser,HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
        List<PostDto> postByCategory = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(postByCategory,HttpStatus.OK);
    }

    @PutMapping ("/posts/update/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
        PostDto postDto1 = this.postService.updatePost(postDto,postId);
        return new ResponseEntity<>(postDto1,HttpStatus.OK);

    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<ApiResponse> deletePostById(@PathVariable("postId") Integer postId) {
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Sucessfully Deleted", true), HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.Page_Number, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.Page_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir

    ){
        PostResponse allPost = this.postService.getAllPost(pageNumber, pageSize,sortBy,sortDir);
        return new ResponseEntity<>( allPost,HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto postById = this.postService.getPostById(postId);
        return new ResponseEntity<>(postById,HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keywords){
        List<PostDto> postDtos = this.postService.searchPosts(keywords);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }

    //Post Image Uplaoad
    @PostMapping("/posts/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadImage(
            @RequestParam("image")MultipartFile image,
            @PathVariable Integer postId
            ) throws IOException {
        PostDto postById = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImages(path, image);
        postById.setImageName(fileName);

        PostDto updatedDto = this.postService.updatePost(postById, postId);
        return  new ResponseEntity<>(updatedDto, HttpStatus.OK);

    }

    // method to serve Image
    @GetMapping(value = "/posts/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable String imageName,
            HttpServletResponse response
    ) throws IOException {
        InputStream resource = this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}

