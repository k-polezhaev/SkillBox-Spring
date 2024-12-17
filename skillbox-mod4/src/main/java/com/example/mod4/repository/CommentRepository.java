package com.example.mod4.repository;

import com.example.mod4.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentByUserId(Long id);

    List<Comment> findByNewsId(Long newsId);

    void deleteCommentByUserId(Long userId);

    @Query("SELECT count(*) FROM com.example.news.model.Comment WHERE news.id= :id")
    Integer getCommentsCountByNewsId(Long id);

}
