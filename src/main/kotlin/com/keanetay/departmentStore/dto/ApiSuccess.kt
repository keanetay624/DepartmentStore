package com.keanetay.departmentStore.dto

import com.keanetay.departmentStore.model.SalesItem
import org.springframework.http.HttpStatus

data class ApiSuccess(
        val status: HttpStatus = HttpStatus.OK,
        val message : String,
        val totalSearchCount: Long,
        val results : List<SalesItem>
)