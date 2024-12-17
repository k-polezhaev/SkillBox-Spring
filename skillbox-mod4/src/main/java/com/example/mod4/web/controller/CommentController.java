package com.example.mod4.web.controller;

import com.example.mod4.mapper.v2.CommentMapperV2;
import com.example.mod4.service.CommentService;
import com.example.mod4.web.model.CommentFilter;
import com.example.mod4.web.model.CommentRequest;
import com.example.mod4.web.model.CommentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentMapperV2 commentMapper;

    public CommentController(CommentService commentService, CommentMapperV2 commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @GetMapping()
    public ResponseEntity<List<CommentResponse>> findByNewsId(CommentFilter filter){
        return ResponseEntity.ok(commentService.findByNewsId(filter)
                .stream()
                .map(commentMapper::commentToResponse)
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody @Valid CommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentMapper.commentToResponse(
                        commentService.create(
                                commentMapper.requestToComment(request)
                        )
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> update(@PathVariable Long id, @RequestBody @Valid CommentRequest request) {
        return ResponseEntity.ok(
                commentMapper.commentToResponse(
                        commentService.update(
                                commentMapper.requestToComment(request).setId(id)
                        )
                )
        );
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        commentService.deleteById(id);
    }
}
