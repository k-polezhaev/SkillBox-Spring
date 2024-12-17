package com.example.mod4.repository;

import com.example.mod4.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findAllByUserId(Long id);
    List<News> findAllByCategoryId(Long id);
    List<News> findAllByUserIdAndCategoryId(Long userId, Long categoryId);
}
