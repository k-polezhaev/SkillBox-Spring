package com.example.mod4.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewsFilter {
    private Long categoryId;
    private Long userId;
}
