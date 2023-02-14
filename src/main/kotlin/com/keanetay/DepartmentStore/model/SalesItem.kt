package com.keanetay.DepartmentStore.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.math.BigDecimal
import java.time.LocalDateTime
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
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM/d/yyyy H:mm")
        val invoiceDate: LocalDateTime,
        val unitPrice: BigDecimal,
        val customerId: Long,
        val country: String
) {

}