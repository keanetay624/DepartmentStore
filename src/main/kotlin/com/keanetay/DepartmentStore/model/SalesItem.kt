package com.keanetay.DepartmentStore.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
@Table
class SalesItem(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,
        val invoiceNo: String,
        val stockCode: String,
        val description: String,
        val quantity: Long,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM/dd/yyyy HH:mm:ss a")
        val invoiceDate: Date,
        val unitPrice: BigDecimal,
        val customerId: Long,
        val country: String
) {

}