package com.keanetay.DepartmentStore.service

import com.keanetay.DepartmentStore.dto.ApiSuccess
import com.keanetay.DepartmentStore.model.SalesItem
import com.keanetay.DepartmentStore.repository.SalesItemRepository
import com.keanetay.DepartmentStore.util.CSVUtil
import mu.KotlinLogging
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
    private val logger = KotlinLogging.logger {}

    fun saveSalesItems(file: MultipartFile): List<SalesItem> {
        logger.info("---- CSVService - saveSalesItems Start ----")
        val list: List<SalesItem>
        try {
            list = file.inputStream.use { inputStream ->
                CSVUtil.readCsv(inputStream)
            }
            salesItemRepository.saveAll(list)
        } catch (e: IOException) {
            logger.info("---- CSVService - IOException ----")
            throw RuntimeException("fail to store csv data: " + e.message)
        }
        logger.info("---- CSVService - saveSalesItems Start ----")
        return list
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