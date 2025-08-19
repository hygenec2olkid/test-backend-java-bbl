package com.api.test_backend_java_bbl.model;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserDetail(
        @NotBlank(message = "name is required")
        String name,

        @NotBlank(message = "username is required")
        String username,

        @NotBlank(message = "email is required")
        String email,
        String phone,
        String website
) {
}
