package com.keanetay.DepartmentStore.repository

import com.keanetay.DepartmentStore.model.SalesItem
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface SalesItemRepository : JpaRepository<SalesItem,Int> {

    override fun findAll(pageable: Pageable): Page<SalesItem> {
        TODO("Not yet implemented")
    }
}