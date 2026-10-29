package com.purnima.campusconnect.student;
import jakarta.validation.constraints.*;
public record StudentRequest(
    @NotBlank @Size(max=100) String name,
    @NotBlank @Email @Size(max=160) String email,
    @NotBlank @Size(max=80) String department,
    @NotNull @Min(2020) @Max(2100) Integer graduationYear
) {}