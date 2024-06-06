package com.dev.explore_blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Category Information")
public class CategoryDto {

    @Schema(description = "")
    private Long id;

    @NotEmpty
    @Size(min = 2, message = "name must contain 2 letters")
    private String name;

    @NotEmpty
    @Size(min = 10, message = "body must contain 2 letters")
    private String description;
}
