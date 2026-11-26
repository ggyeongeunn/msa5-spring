package org.mas.msa5spring.error;

import org.mas.msa5spring.response.GlobalResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;
import java.util.stream.Collectors;

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

    // ===================================================
    // 특정 예외 처리 핸들러 메서드
    // 유효성 체크 예외 관련
    // Exception : 최상위 예외 객체 -> Upcasting되어서 가져옴 -> 자식이 가지고 있는 고유 요소를 가져올 수 없음
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<GlobalResponseDTO<String>> handle(MethodArgumentTypeMismatchException e) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            // BAD_GATEWAY : 상수 -> 상수 쓰는 이유 -> 숫자로 쓰면 오류 시 직접 수정(오탈자 방지)
            .body(
                new GlobalResponseDTO<>(
                    "E01",
                    "유효성 에러 체크",
                    String.format("%s: 필드를 확인해 주세요.", e.getName())
                )
            );

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponseDTO<Map<String, String>>> handle(MethodArgumentNotValidException e) {
    // public Map<String, String> handle(MethodArgumentNotValidException e) {
        Map<String, String> errors = e.getBindingResult()
            .getFieldErrors()
            .stream()
            // collect : 최종 연산 메서드
            .collect(Collectors.toMap(
                // lambda
                // (fieldError) -> fieldError.getField(),
                // method reference
                FieldError::getField,   // 필드명을 가져와서 Map의 Key로 사용
                // validation NotBlank 메세지 출력
                (fieldError) -> fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : "유효하지 않은 값입니다.",
                // existing : 기존값, replacement : 새로운 값
                (existing, replacement) -> existing
            ));
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                new GlobalResponseDTO<>(
                    "E01",
                    "유효성 체크 에러",
                    errors
                )
            );

    }


    // ===================================================
    // 그 외 모든 예외 처리 핸들러 메서드
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
