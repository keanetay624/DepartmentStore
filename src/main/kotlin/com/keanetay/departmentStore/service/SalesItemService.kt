package com.keanetay.departmentStore.service

import com.keanetay.departmentStore.dto.ApiSuccess
import com.keanetay.departmentStore.model.SalesItem
import com.keanetay.departmentStore.repository.SalesItemRepository
import com.keanetay.departmentStore.util.CSVUtil
import mu.KotlinLogging
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@Service
class SalesItemService(
    private val salesItemRepository: SalesItemRepository // Constructor injection is recommended over field injection
) {

    private val logger = KotlinLogging.logger {}

    fun saveSalesItems(items: List<SalesItem>): List<SalesItem> {
        return salesItemRepository.saveAll(items)
    }

    fun saveSalesItems(file: MultipartFile): List<SalesItem> {
        return saveSalesItems(
            getSalesItemsFromFile(file)
        )
    }

    private fun getSalesItemsFromFile(file: MultipartFile): List<SalesItem> {
        try {
            return file.inputStream.use { inputStream ->
                CSVUtil.readCsv(inputStream)
            }
        } catch (e: IOException) {
            throw RuntimeException("fail to store csv data: " + e.message, e)
        }
    }

    fun getSalesItems(
        searchStr: String,
        limit: Int,
        offset: Int
    ): ApiSuccess {
        logger.info("---- CSVService - getSalesItems Start ----")
        val paging: Pageable = PageRequest.of(offset, limit)
        val pageSalesItems: Page<SalesItem> = salesItemRepository.getSalesItemsBySearchStr(searchStr, paging)
        logger.info("---- CSVService - getSalesItems End ----")
        return ApiSuccess(
            HttpStatus.OK,
            "Fetched $limit records containing '$searchStr'",
            pageSalesItems.totalElements,
            pageSalesItems.content
        )
    }
}