package com.example.demo.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorBody {
    private final String error;
}
