package com.keanetay.DepartmentStore.util

import com.keanetay.DepartmentStore.model.SalesItem
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVRecord
import java.io.InputStream
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object CSVUtil {
    private const val PATTERN = "M/d/yyyy H:mm"
    private val formatter = DateTimeFormatter.ofPattern(PATTERN)

    fun readCsv(inputStream: InputStream): List<SalesItem> {
        return CSVFormat.Builder
            .create(CSVFormat.EXCEL)
            .build().parse(inputStream.reader())
            .drop(1) // Dropping the header
            .map {
                printRecord(csvRecord = it)
                println("next record")
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
    }
    fun printRecord(csvRecord: CSVRecord) {
        println(csvRecord.get(0)) // invoiceNo
        println(csvRecord.get(1)) // stockcode
        println(csvRecord.get(2)) // description
        println(csvRecord.get(3)) // quantity
        println(csvRecord.get(4)) // invoiceDate
        println(csvRecord.get(5)) // unitPrice
        println(csvRecord.get(6)) // customerId
        println(csvRecord.get(7)) // country
    }
}