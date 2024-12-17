package com.example.mod4.web.controller;

import com.example.mod4.mapper.v2.NewsMapperV2;
import com.example.mod4.service.CommentService;
import com.example.mod4.service.NewsService;
import com.example.mod4.web.model.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;
    private final NewsMapperV2 newsMapper;

    public NewsController(NewsService newsService, CommentService commentService, NewsMapperV2 newsMapper) {
        this.newsService = newsService;
        this.newsMapper = newsMapper;
    }

    @GetMapping("/filter")
    public ResponseEntity<List<NewsResponse>> filterBy(NewsFilter filter) {
        return ResponseEntity.ok(
                newsService.filterBy(filter).stream()
                        .map(newsMapper::newsToResponse)
                        .collect(Collectors.toList()));
    }

    @GetMapping
    public ResponseEntity<List<NewsResponse>> findAll(PageFilter filter) {
        return ResponseEntity.ok(
                newsService.findAll(filter).stream()
                        .map(newsMapper::newsToResponse)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OneNewsResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                newsMapper.newsToOneNewsResponse(
                        newsService.findById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<NewsResponse> create(@RequestBody @Valid NewsRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newsMapper.newsToResponse(
                        newsService.create(
                                newsMapper.requestToNews(request)
                        )
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsResponse> update(@PathVariable Long id, @RequestBody @Valid NewsRequest newsRequest) {
        return ResponseEntity.ok(
                newsMapper.newsToResponse(
                        newsService.update(
                                newsMapper.requestToNews(newsRequest).setId(id)
                        )
                )
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        newsService.deleteById(id);
    }


}
