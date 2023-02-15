package com.keanetay.DepartmentStore.dto

import org.springframework.http.HttpStatus

data class ApiError(
        val status:HttpStatus,
        val message:String
)