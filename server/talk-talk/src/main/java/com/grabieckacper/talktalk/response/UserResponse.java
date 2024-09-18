package com.grabieckacper.talktalk.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponse(
        Long id,

        String email,

        @JsonProperty("profile_id")
        Long profileId
) {}
