package com.board.defalut.controller;


import com.board.defalut.dto.PostDto;
import com.board.defalut.repo.PostRepository;
import com.board.defalut.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<String> createPost(@RequestBody @Valid PostDto.CreateRequest request) {
        postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(request.getAuthorName());
    }
}
