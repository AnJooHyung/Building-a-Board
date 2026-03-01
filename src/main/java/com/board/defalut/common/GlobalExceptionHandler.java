package com.board.defalut.common;

import com.board.defalut.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.board.defalut.dto.ErrorDto.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // ExceptionHandler를 통해서 낚아챌 예외 클래스를 지정한다.
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        ArrayList<String> msgList = new ArrayList<>();
        msgList.add(e.getMessage());
        // 낚아챌 예외 e에서 메시지를 꺼내어 우리가 만든 포맷에 담는다.
        ErrorResponse response = new ErrorResponse(
                400, // HTTP 상태 코드
                msgList, // 에러가 발생할 때 넣었던 메시지
                LocalDateTime.now() // 발생 시간
        );
        // 프론트엔드에서 일관된 JSON 형태로 반환한다.
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 발생한 모든 필드 에러들을 가져온다.
        List<FieldError> msg = e.getBindingResult().getFieldErrors();
        // 에러 메시지들을 담을 텅 빈 List<String> 바구니를 하나 만든다.
        List<String> msgList = new ArrayList<>();
        // for문을 돌면서 fieldErrors 안의 메시지를 꺼내어 바구니에 담는다.
        for (FieldError fieldError : msg) {
            msgList.add(fieldError.getDefaultMessage());
        }

        ErrorResponse response = ErrorResponse.builder()
                .status(400)
                .messages(msgList)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}

//PostService에서 비즈니스 로직을 처리하다가 뭔가 잘못되어서 throw new IllegalArgumentException("없는 게시글입니다."); 라고 예외를 던집니다.
//
//예외는 Controller를 뚫고 나와서 스프링의 핵심 통제 센터인 **DispatcherServlet**으로 향합니다.
//
//디스패처 서블릿은 클라이언트에게 에러를 던지기 직전에, 스프링 컨테이너를 싹 뒤져서 @RestControllerAdvice가 붙은 클래스를 찾습니다.
//
//그리고 그 안에 @ExceptionHandler(IllegalArgumentException.class)가 있는지 확인한 뒤, 발생한 예외 객체(e)를 파라미터로 넘겨주며 이 메서드를 대신 실행시킵니다.
