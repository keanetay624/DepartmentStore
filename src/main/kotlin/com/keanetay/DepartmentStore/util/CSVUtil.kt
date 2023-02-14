package com.keanetay.DepartmentStore.util

import com.keanetay.DepartmentStore.model.SalesItem
import org.apache.commons.csv.CSVFormat
import java.io.InputStream
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object CSVUtil {
    private const val PATTERN = "MM/d/yyyy H:mm"
    private val formatter = DateTimeFormatter.ofPattern(PATTERN)

    fun readCsv(inputStream: InputStream): List<SalesItem> =
            CSVFormat.Builder
                    .create(CSVFormat.DEFAULT.withQuote(null))
                    .apply {setIgnoreSurroundingSpaces(true)}
                    .build().parse(inputStream.reader())
                    .drop(1) // Dropping the header
                    .map {
                        SalesItem(
                                id = 0,
                                invoiceNo = it[0],
                                stockCode = it[1],
                                description = it[2],
                                quantity = it[3].toLong(),
                                invoiceDate = LocalDateTime.parse(it[4], formatter),
                                unitPrice = BigDecimal(it[5]),
                                customerId = it[6].toLong(),
                                country = it[7]
                        )
                    }
}