package com.keanetay.DepartmentStore.exception

import com.keanetay.DepartmentStore.dto.ApiError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.IllegalArgumentException

@ControllerAdvice
class RestExceptionHandler {
    @ExceptionHandler
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ApiError> {
        val errorMessage = ex.message?.let {
            ApiError(
                HttpStatus.BAD_REQUEST,
                it
            )
        }
        return ResponseEntity<ApiError>(errorMessage, HttpStatus.BAD_REQUEST)
    }
}