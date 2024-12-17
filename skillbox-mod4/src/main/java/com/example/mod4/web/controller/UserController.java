package com.example.mod4.web.controller;

import com.example.mod4.mapper.v2.UserMapperV2;
import com.example.mod4.service.UserService;
import com.example.mod4.web.model.PageFilter;
import com.example.mod4.web.model.UserRequest;
import com.example.mod4.web.model.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapperV2 userMapper;

    public UserController(UserService userService, UserMapperV2 userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll(PageFilter filter){
        return ResponseEntity.ok(
                userService.findAll(filter).stream()
                        .map(userMapper::userToResponse)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                userMapper.userToResponse(
                        userService.findById(id)
                )
        );
    }

    @PostMapping()
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest userRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToResponse(
                        userService.create(
                                userMapper.requestToUser(userRequest)
                        )
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody @Valid UserRequest userRequest){
        return ResponseEntity.ok(
                userMapper.userToResponse(
                        userService.update(
                                userMapper.requestToUser(userRequest).setId(id)
                        )
                )
        );
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        userService.deleteById(id);
    }

}
