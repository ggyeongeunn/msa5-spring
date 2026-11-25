package org.mas.msa5spring.controller;

import org.mas.msa5spring.request.QueryRequestDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class RequestMappingController {
    // users로 요청이 왔을 때
    // user가 단순 조회 -> get방식
    @GetMapping
    public String index() {
        return "유저 조회";
    }

    @PostMapping
    public String store() {
        return "유저 생성";
    }

    @PutMapping
    public String update() {
        return "유저 수정";
    }

    @DeleteMapping
    public String destroy() {
        return "유저 삭제";
    }

    @GetMapping("/query")
    public String query(QueryRequestDTO queryRequestDTO) {
        return String.format("%d : %s",queryRequestDTO.id(), queryRequestDTO.name());
    }

    @GetMapping("/segment/{id}/{name}")
    public String segment(@ModelAttribute QueryRequestDTO queryRequestDTO) {
        return String.format("segment = %d : %s", queryRequestDTO.id(), queryRequestDTO.name());
    }

    // 세그먼트 파라미터 사용 시 path와 겹치지 않도록 주의
    // @GetMapping("/segment/test/my")
    // public String test() {
    //     return "Test";
    // }

    @PostMapping("/form")
    public String form(@ModelAttribute MultipartFile file) {
        // 파일객체에서 관리 -> DTO파일 생성 안해도 됨
        return String.format("%s: %s", file.getContentType(), file.getOriginalFilename());
    }

    @GetMapping("/json")
    public String json(@RequestBody QueryRequestDTO queryRequestDTO) {
        return String.format("json = %d : %s", queryRequestDTO.id(), queryRequestDTO.name());
    }
}
// Query Parameter 방식
// - Annotation X, DTO 객체 생성

// Segment Parameter 방식
// - @PathVariable, 각 키에 맞는 파라미터를 별도로 맵핑 메소드(컨트롤러)의 파라미터로 생성
// - @ModelAttribute, DTO 객체 생성

// Form Data 방식
// - @ModelAttribute, DTO 객체 또는 맵핑 메소드(컨트롤러)의 파라미터

// JSON 방식
// - @RequestBody, DTO 객체