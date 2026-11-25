package org.mas.msa5spring.error;

import org.mas.msa5spring.response.GlobalResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 데이터 타입이 Exception.class -> exception이라는 instance
    // .class -> 객체의 리터럴 타입
    // Exception -> 그냥 타입

    // runtime : 프로세스를 유지하고 있는 상태. 멈추지 않고 계속 돌아감
    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public ResponseEntity<GlobalResponseDTO<Void>> handle(MethodArgumentNotValidException e) {
    //
    // }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponseDTO<Void>> handle(Exception e) {
        return ResponseEntity
            .status(500)
            .body(
                new GlobalResponseDTO<>(
                    "E99", "system error", null
                )
            );
    }
}
