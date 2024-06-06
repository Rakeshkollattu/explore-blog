package com.dev.explore_blog.controller;

import com.dev.explore_blog.payload.LoginDto;
import com.dev.explore_blog.payload.RegisterDto;
import com.dev.explore_blog.payload.TokenResponse;
import com.dev.explore_blog.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exploreBlog/auth")
@Tag(
        name = "Rest APIs for Authentication"
)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping({"/login", "/signIn"})
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        TokenResponse response = new TokenResponse();
        response.setAccessToken(token);
        return ResponseEntity.ok(response);
    }

    @PostMapping({"/register", "/signUp"})
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
