package com.keanetay.DepartmentStore.controller

import com.keanetay.DepartmentStore.dto.ApiSuccess
import com.keanetay.DepartmentStore.model.SalesItem
import com.keanetay.DepartmentStore.service.CSVService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@RestController
@RequestMapping("item")
@Validated
@CrossOrigin(origins = ["http://127.0.0.1:5173"])
class SalesItemController {
    @Autowired
    lateinit var csvService: CSVService
    private val logger = KotlinLogging.logger {}

    @PostMapping("upload")
    fun uploadFile(@RequestParam("file") file: MultipartFile): ResponseEntity<ApiSuccess?>? {
        logger.info("---- SalesItemController - uploadFile Start ----")
        csvService.saveSalesItems(file)
        logger.info("---- SalesItemController - uploadFile end ----")
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
        @RequestParam(name = "searchStr")  searchStr: String,
        @RequestParam(name = "limit") @Max(50) @Min(0) limit: Int,
        @RequestParam(name = "offset") @Min(0) offset: Int
    ): ResponseEntity<ApiSuccess?>? {
        logger.info("---- SalesItemController - getSalesItems Start ----")
        val successResponse: ApiSuccess = csvService.getSalesItems(searchStr, limit, offset)
        logger.info("---- SalesItemController - getSalesItems End ----")
        return ResponseEntity.status(HttpStatus.OK).body<ApiSuccess?>(successResponse)
    }
}