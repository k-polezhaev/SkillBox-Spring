package com.example.mod4.service;

import com.example.mod4.model.NewsCategory;
import com.example.mod4.web.model.PageFilter;

import java.util.List;

public interface NewsCategoryService {
    List<NewsCategory> findAll(PageFilter filter);

    NewsCategory findById(Long id);

    NewsCategory create(NewsCategory newsCategory);

    NewsCategory update(NewsCategory newsCategory);

    void deleteById(Long id);

}
