package com.blog.blogapplication.security;

import com.blog.blogapplication.entities.User;
import com.blog.blogapplication.exceptions.ResourceNotFoundException;
import com.blog.blogapplication.respositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // loading data from DB as usernamae
        User user = this.userRepo.findByEmail(username).
                orElseThrow(() -> new ResourceNotFoundException("User", "email  " + username, 0));
        return user;
    }
}
