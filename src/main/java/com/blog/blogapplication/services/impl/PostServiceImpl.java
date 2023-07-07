package com.blog.blogapplication.services.impl;

import com.blog.blogapplication.entities.Category;
import com.blog.blogapplication.entities.Post;
import com.blog.blogapplication.entities.User;
import com.blog.blogapplication.exceptions.ResourceNotFoundException;
import com.blog.blogapplication.payloads.PostDto;
import com.blog.blogapplication.payloads.PostResponse;
import com.blog.blogapplication.respositories.CategoryRepo;
import com.blog.blogapplication.respositories.PostRepo;
import com.blog.blogapplication.respositories.UserRepo;
import com.blog.blogapplication.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto,Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "userId", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", "categoryId", categoryId));
        Post p1 = this.modelMapper.map(postDto, Post.class);
        p1.setImageName("default.png");
        p1.setAddedDate(new Date());
        p1.setUser(user);
        p1.setCategory(category);
        Post save = this.postRepo.save(p1);
        return this.modelMapper.map(save,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post", "postId", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post save = this.postRepo.save(post);
        return this.modelMapper.map(save,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post", "postId", postId));
        this.postRepo.delete(post);
    }



    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
//        Pageable p1 = (Pageable) PageRequest.of(pageNumber,pageSize);
        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        } else if (sortDir.equalsIgnoreCase("dsc")) {
            sort=Sort.by(sortBy).descending();
        }
        Pageable p1 = PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> page = this.postRepo.findAll(p1);
        List<Post> all = page.getContent();
        List<PostDto> collect = all.stream().map((post) ->
                this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(collect);
        postResponse.setPageNumber(page.getNumber());
        postResponse.setPageSize(page.getSize());
        postResponse.setTotalElements(page.getTotalElements());
        postResponse.setTotalPages(page.getTotalPages());
        postResponse.setLastPage(page.isLast());


        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post", "postId", postId));
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", "categoryId", categoryId));
        List<Post> byCategory = this.postRepo.findByCategory(category);
        List<PostDto> collect = byCategory.stream().
                map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "userId", userId));
        List<Post> byUser = this.postRepo.findByUser(user);
        List<PostDto> collect = byUser.stream().
                map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = this.postRepo.findByTitleContaining(keyword);
        List<PostDto> collect = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return collect;
    }
}
