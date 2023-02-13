package com.keanetay.DepartmentStore.repository

import com.keanetay.DepartmentStore.model.SalesItem
import org.springframework.data.jpa.repository.JpaRepository

interface SalesItemRepository : JpaRepository<SalesItem,Int> {
}