package org.mas.msa5spring.request;

import jakarta.validation.constraints.*;

public record RegistrationRequestDTO(
    @NotBlank(message = "required email")
    @Pattern(regexp = "^[a-z]{4,8}$", message = "error email pattern")   // 소문자 + 4-8글자만 허용
    String email,

    @NotBlank(message = "required password")
    @Size(min =  2, max = 10, message = "insert 2-10 type password")
    String password,

    String name,

    @Min(value = 0, message = "minimal value of age is 0")
    @Max(value = 999, message = "maximal value of age is 999")
    Integer age
) {}
