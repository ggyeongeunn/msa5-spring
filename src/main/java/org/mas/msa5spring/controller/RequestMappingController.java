package org.mas.msa5spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestMappingController {
    // users로 요청이 왔을 때
    // user가 단순 조회 -> get방식
    @GetMapping("/users")
    public String index() {
        return "유저 조회";
    }

    @PostMapping("/users")
    public String store() {
        return "유저 생성";
    }
}
