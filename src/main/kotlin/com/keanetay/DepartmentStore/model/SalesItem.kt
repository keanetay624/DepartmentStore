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
    val invoiceNo: String? = null,
    val stockCode: String? = null,
    val description: String? = null,
    val quantity: Long? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/d/yyyy H:mm")
    val invoiceDate: LocalDateTime? = null,
    val unitPrice: BigDecimal? = null,
    val customerId: Long? = null,
    val country: String? = null
)