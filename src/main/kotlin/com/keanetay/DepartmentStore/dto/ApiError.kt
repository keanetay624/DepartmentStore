package com.keanetay.DepartmentStore.dto

import org.springframework.http.HttpStatus

class ApiError(
    val status: HttpStatus,
    val message: String?,
    val cause: Throwable?
)