package com.board.defalut.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorDto {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class ErrorResponse {
        private int status;

        private List<String> messages;

        private LocalDateTime timestamp;
    }
}
