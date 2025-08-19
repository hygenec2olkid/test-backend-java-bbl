package com.api.test_backend_java_bbl.model;

public record CreateNewUserRequest(
         Long id,
         String name,
         String username,
         String email,
         String phone,
         String website
) {
}
