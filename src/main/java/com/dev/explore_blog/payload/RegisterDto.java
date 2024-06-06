package com.dev.explore_blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Registration Details")
public class RegisterDto {

    private Long id;

    @NotEmpty
    @Size(min=2, message = "Name should have at least 2 characters")
    private String name;

    @NotEmpty
    @Size(min=2, message = "username should have at least 2 characters")
    private String username;

    @NotEmpty(message = "Required a valid email")
    @Email
    private String email;

    @NotEmpty
    private String password;
}
