package com.keanetay.DepartmentStore.controller

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
internal class SalesItemControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Nested
    @DisplayName("getSalesItems()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetSalesItems {

        @Test
        fun `should return 200`() {
            mockMvc.get("/api/v1") {
                param("searchStr", "")
                param("limit", "50")
                param("offset", "0")
            }
                .andExpect { status { isOk()}}
        }

        @Test
        fun `should return 400 missing searchStr`() {
            mockMvc.get("/api/v1") {
                param("limit", "50")
                param("offset", "0")
            }
                .andExpect { status { is4xxClientError()}}
        }

        @Test
        fun `should return 400 missing limit`() {
            mockMvc.get("/api/v1") {
                param("searchStr", "")
                param("offset", "0")
            }
                .andExpect { status { is4xxClientError()}}
        }

        @Test
        fun `should return 400 missing offset`() {
            mockMvc.get("/api/v1") {
                param("searchStr", "")
                param("limit", "0")
            }
                .andExpect { status { is4xxClientError()}}
        }

        @Test
        fun `should return 400 missing all params`() {
            mockMvc.get("/api/v1")
                .andExpect { status { is4xxClientError()}}
        }

        @Test
        fun `should return 400 invalid offset`() {
            mockMvc.get("/api/v1") {
                param("searchStr", "")
                param("offset", "k")
                param("limit", "50")
            }
                .andExpect { status { is4xxClientError()}}
        }

        @Test
        fun `should return 400 invalid limit`() {
            mockMvc.get("/api/v1") {
                param("searchStr", "")
                param("offset", "0")
                param("limit", "k")
            }
                .andExpect { status { is4xxClientError()}}
        }

        @Test
        fun `should return 400 limit less than 1`() {
            mockMvc.get("/api/v1") {
                param("searchStr", "")
                param("offset", "0")
                param("limit", "0")
            }
                .andExpect { status { is4xxClientError()}}
        }
    }
}