package com.keanetay.DepartmentStore.util

import com.keanetay.DepartmentStore.model.SalesItem
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.File


internal class CSVUtilTest {
    private val filepath:String = System.getProperty("user.dir") + "/src/main/resources/testFiles/Test_Exception.csv"
    @Test
    @DisplayName("Should return 1 item")
    fun readCsv() {
        val file = File(filepath)
        val salesItems:List<SalesItem>
        file.inputStream().use {
            salesItems = CSVUtil.readCsv(it)
        }
        assertEquals(salesItems.size,1)
    }
}