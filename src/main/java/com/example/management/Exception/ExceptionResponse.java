package com.example.management.Exception;

import java.time.LocalDateTime;

public record ExceptionResponse(LocalDateTime timestamp, String message, String error, String details) {
}
