package com.blog.blogapplication.controllers;

import com.blog.blogapplication.payloads.ApiResponse;
import com.blog.blogapplication.payloads.UserDto;
import com.blog.blogapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    //Post CreateUser
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto userDto1 = this.userService.createUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);

    }

    // Put/update User
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid  @RequestBody UserDto userDto,
                                                @PathVariable int userId){
        UserDto updatedUser = this.userService.updateUser(userDto, userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);

    }

    // Get All Users
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> allUsers = this.userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int userId){
        return new ResponseEntity<>(this.userService.getUserById(userId),HttpStatus.OK);
    }
    // Delete UserById
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable("userId") int userId){
        this.userService.deleteUserById(userId);
        return new ResponseEntity<>(new ApiResponse("Sucessfully Deleted",true),HttpStatus.OK);
    }

}
