package com.grabieckacper.talktalk.response;

import java.util.List;

public record ErrorResponse(
        Integer code,
        List<String> messages
) {}
