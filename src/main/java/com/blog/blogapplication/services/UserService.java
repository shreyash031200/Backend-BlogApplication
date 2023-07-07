package com.blog.blogapplication.services;

import com.blog.blogapplication.payloads.UserDto;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface UserService {

    public UserDto createUser(UserDto userDto);
    public UserDto updateUser(UserDto userDto,Integer userId);
    public UserDto getUserById(Integer userId);
    public List<UserDto> getAllUsers();
    public  void deleteUserById(Integer userId);
}
