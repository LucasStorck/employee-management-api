package com.lucas.employee_management.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record EmployeeResponseDto(
        Long id,
        @NotNull
        @Size(max = 64)
        String name,
        @NotNull
        @Size(max = 64)
        String role,
        @NotNull
        @Size(max = 64)
        String department) {
}
