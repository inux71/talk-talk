package com.grabieckacper.talktalk.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @Email
        @NotNull
        String email,

        @NotBlank
        @Size(min = 8)
        String password,

        @NotBlank
        @JsonProperty("first_name")
        String firstName,

        @NotBlank
        @JsonProperty("last_name")
        String lastName
) {}
