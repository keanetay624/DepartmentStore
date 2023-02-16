package com.keanetay.DepartmentStore.service

import com.keanetay.DepartmentStore.dto.ApiSuccess
import com.keanetay.DepartmentStore.model.SalesItem
import com.keanetay.DepartmentStore.repository.SalesItemRepository
import com.keanetay.DepartmentStore.util.CSVUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@Service
class CSVService {
    @Autowired
    lateinit var salesItemRepository: SalesItemRepository

    fun saveSalesItems(file: MultipartFile): List<SalesItem> {
        val list: List<SalesItem>
        try {
            list = file.inputStream.use { inputStream ->
                CSVUtil.readCsv(inputStream)
            }
            salesItemRepository.saveAll(list)
        } catch (e: IOException) {
            throw RuntimeException("fail to store csv data: " + e.message)
        }
        return list
    }

    fun getSalesItems(
        searchStr: String,
        limit: Int,
        offset: Int
    ): ApiSuccess {
        val paging: Pageable = PageRequest.of(offset, limit)
        val pageSalesItems: Page<SalesItem> = salesItemRepository.getSalesItemsBySearchStr(searchStr, paging)
        return ApiSuccess(
            HttpStatus.OK,
            "Fetched $limit records containing '$searchStr'",
            pageSalesItems.totalElements,
            pageSalesItems.content
        )
    }
}