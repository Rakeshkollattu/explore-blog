package com.dev.explore_blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Comment Information")
public class CommentDto {

    private Long id;

    @NotEmpty(message = "name must not be empty")
    private String name;

    @NotEmpty(message = "email must not be empty")
    @Email
    private String email;

    @NotEmpty
    @Size(min=2,message = "body must contain 2 letters")
    private String body;

}
