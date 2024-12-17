package com.example.mod4.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsRequest {

    @NotBlank(message = "Title must be not blank!")
    private String title;

    @NotBlank(message = "Title must be not blank!")
    private String body;

    @NotNull(message = "CategoryId must be not null!")
    private Long categoryId;
}
