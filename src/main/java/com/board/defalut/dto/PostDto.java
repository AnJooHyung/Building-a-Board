package com.board.defalut.dto;

import com.board.defalut.entity.Post;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;


public class PostDto {
    @Getter
    @AllArgsConstructor
    @Builder
    public static class CreateRequest {
        @NotBlank
        private String title; // 제목

        private String content; // 내용

        private String authorName; // 작성자 이름

        // 게시글을 만든다는건 결국 post하는 영역 -> 생성시간을 조작할 가능성이 높음
        // 서버가 저장하는 순간의 시간을 넣는게 정답!!
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class PageListResponse {
        private Long post_id; // 사용자 ID
        private String title; // 게시글 제목
        private LocalDateTime created_time; // 만든시간
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class PostDetailResponse {
        private Long post_id;
        private String title;
        private String content;
        private LocalDateTime created_time;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class PostUpdateRequest {
        private String title;
        private String content;
    }
}

