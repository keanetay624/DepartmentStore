package com.keanetay.DepartmentStore.repository

import com.keanetay.DepartmentStore.model.SalesItem
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SalesItemRepository : JpaRepository<SalesItem,Int> {

    override fun findAll(pageable: Pageable): Page<SalesItem> {
        TODO("Not yet implemented")
    }

    @Query(value = "SELECT * FROM Sales_Item WHERE country like %?1%",
        nativeQuery = true)
    fun getSalesItemsBySearchStr(searchStr: String) : List<SalesItem>

}