package com.dev.explore_blog.payload;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Post Information")
public class PostDto {

    private Long id;

    @NotEmpty
    @Size(min=2, message = "Post title should have at least 2 characters")
    @Schema(description = "Post Title")
    private String title;

    @NotEmpty
    @Size(min=2, message = "Post description should have at least 2 characters")
    @Schema(description = "Post Description")
    private String description;

    @NotEmpty
    @Schema(description = "Post Content")
    private String content;

    @Schema(description = "Post Category")
    private Long categoryId;

    private Set<CommentDto> comments;
}

