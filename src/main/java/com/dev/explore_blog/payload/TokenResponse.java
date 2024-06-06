package com.dev.explore_blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Jwt Token Information")
public class TokenResponse {
    private String tokenType = "Bearer";
    private String accessToken;
}
