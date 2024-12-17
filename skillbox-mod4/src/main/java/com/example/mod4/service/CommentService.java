package com.example.mod4.service;

import com.example.mod4.model.Comment;
import com.example.mod4.web.model.CommentFilter;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();

    Comment findById(Long id);

    Comment create(Comment comment);

    Comment update(Comment comment);

    void deleteById(Long id);

    List<Comment> findByNewsId(CommentFilter filter);

    Integer getCommentCountByNewsId(Long newsId);

}
