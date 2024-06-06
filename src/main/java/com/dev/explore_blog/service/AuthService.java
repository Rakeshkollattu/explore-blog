package com.dev.explore_blog.service;

import com.dev.explore_blog.payload.LoginDto;
import com.dev.explore_blog.payload.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);

}
