package com.keanetay.DepartmentStore.repository

import com.keanetay.DepartmentStore.model.SalesItem
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SalesItemRepository : JpaRepository<SalesItem, Int> {

    override fun findAll(pageable: Pageable): Page<SalesItem> {
        TODO("Not yet implemented")
    }

    @Query(
        value = "SELECT * FROM Sales_Item WHERE (country ilike %?1% " +
                "OR invoice_no ilike %?1% OR stock_code ilike %?1% " +
                "OR description ilike %?1% OR quantity ilike %?1% " +
                "OR unit_price ilike %?1% OR customer_id ilike %?1%)",
        nativeQuery = true
    )
    fun getSalesItemsBySearchStr(searchStr: String, pageable: Pageable): Page<SalesItem>
}