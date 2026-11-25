package org.mas.msa5spring.response;

public record GlobalResponseDTO<T>(
    String code,    // 어디서 오류났는지 코드로 확인
    String message,     // 해당 코드에 대한 메세지 출력
    T result
) {}
