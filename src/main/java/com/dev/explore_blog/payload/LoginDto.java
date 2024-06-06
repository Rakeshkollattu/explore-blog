package com.dev.explore_blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Login details")
public class LoginDto {

    @NotEmpty(message="must have a valid email or user name")
    private String usernameOrEmail;

    @NotEmpty(message="use password to proceed")
    private String password;
}
