package com.project.blogapp.service;

import com.project.blogapp.payload.LoginDto;
import com.project.blogapp.payload.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
