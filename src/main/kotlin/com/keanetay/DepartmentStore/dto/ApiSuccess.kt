package com.keanetay.DepartmentStore.dto

import com.keanetay.DepartmentStore.model.SalesItem
import org.springframework.http.HttpStatus

class ApiSuccess(
        val status: HttpStatus = HttpStatus.OK,
        val message : String,
        val totalSearchCount: Long,
        val results : List<SalesItem>
)