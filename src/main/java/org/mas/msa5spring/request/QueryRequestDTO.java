package org.mas.msa5spring.request;

public record QueryRequestDTO(
    Long id,    // null을 처리하기 위해 객체타입으로 지정
    String name
) {}
