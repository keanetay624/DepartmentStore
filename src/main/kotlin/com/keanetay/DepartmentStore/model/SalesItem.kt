package com.keanetay.DepartmentStore.model

import java.math.BigDecimal
import java.util.*

class SalesItem(
        val invoiceNo: String,
        val stockCode: String,
        val description: String,
        val quantity: Long,
        val invoiceDate: Date,
        val unitPrice: BigDecimal,
        val customerId: Long,
        val country: String
) {

}