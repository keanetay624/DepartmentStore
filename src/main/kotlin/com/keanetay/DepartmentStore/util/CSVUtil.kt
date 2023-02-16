package com.keanetay.DepartmentStore.util

import com.keanetay.DepartmentStore.model.SalesItem
import mu.KotlinLogging
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.InputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object CSVUtil {
    private const val PATTERN = "M/d/yyyy H:mm"
    private val formatter = DateTimeFormatter.ofPattern(PATTERN)
    private val logger = KotlinLogging.logger {}

    fun readCsv(inputStream: InputStream): List<SalesItem> {
        logger.info("---- CSVUtil - readCsv Start ----")
        val salesItemList:MutableList<SalesItem> = mutableListOf<SalesItem>()
        val parser: CSVParser = CSVFormat.Builder
            .create(CSVFormat.EXCEL)
            .setHeader()
            .setSkipHeaderRecord(true)
            .build()
            .parse(inputStream.reader())

        for (csvRecord in parser) {
            try {
                logger.info("attempting to cast into sales item")
                val item = SalesItem(
                    id = 0,
                    invoiceNo = csvRecord[0],
                    stockCode = csvRecord[1],
                    description = csvRecord[2],
                    quantity = csvRecord[3].toLongOrNull(),
                    invoiceDate = LocalDateTime.parse(csvRecord[4], formatter),
                    unitPrice = csvRecord[5].toBigDecimalOrNull(),
                    customerId = csvRecord[6].toLongOrNull(),
                    country = csvRecord[7]
                )
                salesItemList.add(item)
            } catch (ex: DateTimeParseException) {
                val invoiceNo:String = csvRecord[0]
                logger.warn("failed to parse datetime for invoiceNo: $invoiceNo")
            }
        }
        logger.info("---- CSVUtil - readCsv End ----")
        return salesItemList
    }


}