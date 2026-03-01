package com.board.defalut.service;

import com.board.defalut.dto.PostDto;
import com.board.defalut.entity.Post;
import com.board.defalut.repo.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Post createPost(PostDto.CreateRequest Request) {
        Post post = Post.builder()
                .title(Request.getTitle())
                .content(Request.getContent())
                .build();

        postRepository.save(post);

        return post;
    }
}
