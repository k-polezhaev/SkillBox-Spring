package com.example.mod4.service;

import com.example.mod4.model.News;
import com.example.mod4.web.model.NewsFilter;
import com.example.mod4.web.model.PageFilter;

import java.util.List;

public interface NewsService {

    List<News> filterBy(NewsFilter filter);
    List<News> findAll(PageFilter filter);

    News findById(Long id);

    News create(News news);

    News update(News news);

    void deleteById(Long id);

}
