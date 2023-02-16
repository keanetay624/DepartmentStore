package com.keanetay.DepartmentStore.controller

import com.keanetay.DepartmentStore.dto.ApiSuccess
import com.keanetay.DepartmentStore.model.SalesItem
import com.keanetay.DepartmentStore.service.CSVService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("api/v1")
@Validated
class SalesItemController {
    @Autowired
    lateinit var csvService: CSVService

    @PostMapping("upload")
    fun uploadFile(@RequestParam("file") file: MultipartFile): ResponseEntity<ApiSuccess?>? {
        csvService.saveSalesItems(file)
        return ResponseEntity.status(HttpStatus.OK).body<ApiSuccess?>(
            ApiSuccess(
                message = "Uploaded Successfully: " + file.originalFilename,
                results = listOf<SalesItem>(),
                totalSearchCount = 0
            ) // not returning whole list otherwise payload is too large
        )
    }

    @GetMapping
    fun getSalesItems(
        @RequestParam(name = "searchStr") searchStr: String,
        @RequestParam(name = "limit") limit: Int,
        @RequestParam(name = "offset") offset: Int
    ): ResponseEntity<ApiSuccess?>? {
        val successResponse: ApiSuccess = csvService.getSalesItems(searchStr, limit, offset)
        return ResponseEntity.status(HttpStatus.OK).body<ApiSuccess?>(successResponse)
    }
}