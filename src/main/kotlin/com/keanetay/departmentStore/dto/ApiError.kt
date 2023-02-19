package com.keanetay.departmentStore.dto

import org.springframework.http.HttpStatus

data class ApiError(
    val status: HttpStatus,
    val message: String?,
    val cause: Throwable?
)