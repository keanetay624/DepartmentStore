package com.keanetay.DepartmentStore.util

import com.keanetay.DepartmentStore.model.SalesItem
import mu.KotlinLogging
import org.apache.commons.csv.CSVFormat
import java.io.InputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object CSVUtil {
    private const val PATTERN = "M/d/yyyy H:mm"
    private val formatter = DateTimeFormatter.ofPattern(PATTERN)
    private val logger = KotlinLogging.logger {}

    fun readCsv(inputStream: InputStream): List<SalesItem> {
        logger.info("---- CSVUtil - readCsv Start ----")
        val salesItemList: List<SalesItem> = CSVFormat.Builder
            .create(CSVFormat.EXCEL)
            .build().parse(inputStream.reader())
            .drop(1) // Dropping the header
            .map {
                SalesItem(
                    id = 0,
                    invoiceNo = it[0],
                    stockCode = it[1],
                    description = it[2],
                    quantity = it[3].toLongOrNull(),
                    invoiceDate = LocalDateTime.parse(it[4], formatter),
                    unitPrice = it[5].toBigDecimalOrNull(),
                    customerId = it[6].toLongOrNull(),
                    country = it[7]
                )
            }
        logger.info("---- CSVUtil - readCsv End ----")
        return salesItemList
    }
}