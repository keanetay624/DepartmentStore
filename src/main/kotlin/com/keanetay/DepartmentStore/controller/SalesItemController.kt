package com.keanetay.DepartmentStore.controller

import com.keanetay.DepartmentStore.model.SalesItem
import com.keanetay.DepartmentStore.repository.SalesItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1")
class SalesItemController {
    @Autowired
    lateinit var salesItemRepository : SalesItemRepository
    @PostMapping("/saveSalesItem")
    fun save(@RequestBody salesItem: SalesItem):String {
        salesItemRepository.save(salesItem)
        return "Sales Item saved!"
    }
}