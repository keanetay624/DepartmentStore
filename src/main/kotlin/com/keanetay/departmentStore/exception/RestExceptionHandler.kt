package com.keanetay.departmentStore.exception

import com.keanetay.departmentStore.dto.ApiError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.time.format.DateTimeParseException
import javax.validation.ConstraintViolationException

@ControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(DateTimeParseException::class)
    protected fun handleDateTimeParseException(
        exception: DateTimeParseException,
        request: WebRequest?
    ): ResponseEntity<ApiError> {
        val errorMessage =
            ApiError(
                status = HttpStatus.BAD_REQUEST,
                message = "DateTimeParseException - " + exception.message,
                cause = exception.cause
            )
        return ResponseEntity<ApiError>(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    protected fun handleConstraintViolationException(
        exception: ConstraintViolationException,
        request: WebRequest?
    ): ResponseEntity<ApiError> {
        val errorMessage =
            ApiError(
                status = HttpStatus.BAD_REQUEST,
                message = "ConstraintViolationException - " + exception.message,
                cause = exception.cause
            )
        return ResponseEntity<ApiError>(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ApiError> {
        val errorMessage =
            ApiError(
                status = HttpStatus.BAD_REQUEST,
                message = "IllegalArgumentException - " + ex.message,
                cause = ex.cause
            )
        return ResponseEntity<ApiError>(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleNumberFormatException(ex: NumberFormatException): ResponseEntity<ApiError> {
        val errorMessage =
            ApiError(
                status = HttpStatus.BAD_REQUEST,
                message = "NumberFormatException - " + ex.message,
                cause = ex.cause
            )
        return ResponseEntity<ApiError>(errorMessage, HttpStatus.BAD_REQUEST)
    }
}