package com.board.defalut.service;

import com.board.defalut.dto.PostDto;
import com.board.defalut.entity.Post;
import com.board.defalut.repo.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.board.defalut.dto.PostDto.*;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Post createPost(CreateRequest Request) {
        Post post = Post.builder()
                .title(Request.getTitle())
                .content(Request.getContent())
                .build();

        postRepository.save(post);

        return post;
    }

    @Transactional(readOnly = true)
    public Page<PageListResponse> getPostList(Pageable pageable) {
        // Repository에서 Page<Post> 상자를 가져온다.
        Page<Post> posts = postRepository.findAll(pageable);

        // 상자 안의 Post를 DTO로 변환한다.
        Page<PageListResponse> dtoPage = posts.map(post -> {
            PageListResponse response = PageListResponse.builder()
                    .post_id(post.getPost_id())
                    .title(post.getTitle())
                    .created_time(post.getCreated_time())
                    .build();
            return response;
        });

        return dtoPage;
    }

    @Transactional(readOnly = true)
    public PostDetailResponse getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));

        PostDetailResponse postDetailResponse = PostDetailResponse.builder()
                .post_id(post.getPost_id())
                .title(post.getTitle())
                .content(post.getContent())
                .created_time(post.getCreated_time())
                .build();

        return postDetailResponse;
    }
}
