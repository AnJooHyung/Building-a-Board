package com.board.defalut.dto;

import com.board.defalut.entity.Post;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;


public class PostDto {
    @Getter
    public static class CreateRequest {
        @NotBlank
        private String title; // 제목

        private String content; // 내용

        private String authorName; // 작성자 이름
    }
}

