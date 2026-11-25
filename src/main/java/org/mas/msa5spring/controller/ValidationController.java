package org.mas.msa5spring.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.mas.msa5spring.request.RegistrationRequestDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ValidationController {
    @GetMapping("/validation")
    public String validation(@Valid @RequestBody RegistrationRequestDTO registrationRequestDTO) {
        return "유효성 검사";
    }

    @GetMapping("/validation/{id}")
    public String validationSegment(
        @Min (value = 1, message = "over 1") @PathVariable Long id
    ) {
        return id.toString();
    }
}
