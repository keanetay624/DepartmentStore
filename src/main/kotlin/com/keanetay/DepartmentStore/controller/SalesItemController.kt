package com.keanetay.DepartmentStore.controller

import com.keanetay.DepartmentStore.model.SalesItem
import com.keanetay.DepartmentStore.service.CSVService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("api/v1")
class SalesItemController {
    @Autowired
    lateinit var csvService : CSVService

    @PostMapping("upload")
    fun uploadFile(@RequestParam("file") file: MultipartFile): ResponseEntity<String?>? {
        var message: String
        val error: String
        if (file.isEmpty) {
            message = "File: " + file.originalFilename + " is empty!"
            error = "Empty files are not permitted"
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body<String?>(message)
        }
//        if (!CSVUtil.hasCSVFormat(file)) {
//            message = "Please upload a csv file!"
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body<String?>(message)
//        }
        return try {
            val list : List<SalesItem> = csvService.saveSalesItems(file)
            message = "Uploaded the file successfully: " + file.originalFilename
            ResponseEntity.status(HttpStatus.OK).body<String?>(message)
        } catch (e: Exception) {
            e.printStackTrace()
            message = "Could not upload the file: " + file.originalFilename + "!"
            ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body<String?>(message)
        }
    }
}