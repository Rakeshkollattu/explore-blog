package com.dev.explore_blog.service.impl;

import com.dev.explore_blog.entity.Role;
import com.dev.explore_blog.entity.User;
import com.dev.explore_blog.exception.ExploreApiException;
import com.dev.explore_blog.payload.LoginDto;
import com.dev.explore_blog.payload.RegisterDto;
import com.dev.explore_blog.repository.RoleRepository;
import com.dev.explore_blog.repository.UserRepository;
import com.dev.explore_blog.security.JwtTokenProvider;
import com.dev.explore_blog.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {

        // calling authentication by setting up authentication manager
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        // setting security context with authentication
//    //moved to jwt//    SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateJwtToken(authentication);
    }

    @Override
    public String register(RegisterDto registerDto) {

        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new ExploreApiException(HttpStatus.BAD_REQUEST, " username is already exists! ");
        }

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new ExploreApiException(HttpStatus.BAD_REQUEST, " email already exists! ");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
        userRole.ifPresent(role -> roles.add(userRole.get()));
        user.setRoles(roles);
        userRepository.save(user);
        return registerDto.getEmail() + " : registered successfully! ";
    }
}
