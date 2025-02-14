package com.dev.explore_blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Error Details Information")
public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String details;
}
