package com.board.defalut.controller;


import com.board.defalut.dto.PostDto;
import com.board.defalut.dto.PostDto.PostDetailResponse;
import com.board.defalut.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<Page> getPostList(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostList(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDetailResponse> getPost(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPost(id));
    }
}
