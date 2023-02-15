package com.keanetay.DepartmentStore.util

import com.keanetay.DepartmentStore.model.SalesItem
import org.apache.commons.csv.CSVFormat
import java.io.InputStream
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object CSVUtil {
    private const val PATTERN = "M/d/yyyy H:mm"
    private val formatter = DateTimeFormatter.ofPattern(PATTERN)

        fun readCsv(inputStream: InputStream): List<SalesItem>  {
            return CSVFormat.Builder
                            .create(CSVFormat.EXCEL)
                            .build().parse(inputStream.reader())
                            .drop(1) // Dropping the header
                            .map {
                                println(it.get(0)) // invoiceNo
                                println(it.get(1)) // stockcode
                                println(it.get(2)) // description
                                println(it.get(3)) // quantity
                                println(it.get(4)) // invoiceDate
                                println(it.get(5)) // unitPrice
                                println(it.get(6)) // customerId
                                println(it.get(7)) // country

                                var quantityString = it[3]
                                var quantityLong: Long;
                                if (quantityString != null && !quantityString.isEmpty()) {
                                    quantityLong = quantityString.toLong()
                                } else {
                                    quantityLong = "0".toLong()
                                }

                                var unitPriceString = it[5]
                                var untiPriceDecimal: BigDecimal;
                                if (unitPriceString != null && !unitPriceString.isEmpty()) {
                                    untiPriceDecimal = BigDecimal(unitPriceString)
                                } else {
                                    untiPriceDecimal = BigDecimal(0)
                                }

                                var customerIdString = it[6]
                                var customerIdLong: Long;
                                if (customerIdString != null && !customerIdString.isEmpty()) {
                                    customerIdLong = customerIdString.toLong()
                                } else {
                                    customerIdLong = "0".toLong()
                                }
                                println("next record")
                                SalesItem(
                                        id = 0,
                                        invoiceNo = it[0],
                                        stockCode = it[1],
                                        description = it[2],
                                        quantity = quantityLong,
                                        invoiceDate = LocalDateTime.parse(it[4], formatter),
                                        unitPrice = untiPriceDecimal,
                                        customerId = customerIdLong,
                                        country = it[7]
                                )
                            }
        }
}