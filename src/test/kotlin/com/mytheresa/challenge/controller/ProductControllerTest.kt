package com.mytheresa.challenge.controller

import com.mytheresa.challenge.repository.IProductRepository
import com.mytheresa.challenge.service.impl.ProductServiceImpl
import com.mytheresa.challenge.utils.MockitoUtils.anyObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal

@WebMvcTest(controllers = [ProductsController::class])
class ProductControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var productRepository: IProductRepository

    @MockBean
    lateinit var productService: ProductServiceImpl

    @BeforeEach
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @ParameterizedTest
    @ValueSource(strings = ["Boots", "Hats", "sandals", "shoes"])
    fun `GIVEN a valid request WHEN endpoint products called THEN success`(category: String) {
        val priceLessThan = BigDecimal.TEN
        Mockito.`when`(productService.process(category, priceLessThan)).thenReturn(listOf())

        mockMvc
            .perform(
                get("/products")
                    .param("category", category)
                    .param("price_less_than", priceLessThan.toString())
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().`is`(200))
    }

    @Test
    fun `GIVEN a invalid request WHEN endpoint products called THEN exception`() {
        val priceLessThan = BigDecimal.TEN

        mockMvc
            .perform(
                get("/products")
                    .param("price_less_than", priceLessThan.toString())
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().`is`(400))
    }

    @ParameterizedTest
    @ValueSource(strings = ["Boots", "Hats", "sandals", "shoes"])
    fun `GIVEN a request with category only WHEN endpoint products called THEN success`(category: String) {
        val priceLessThan = BigDecimal.TEN
        Mockito.`when`(productService.process(category, priceLessThan)).thenReturn(listOf())

        mockMvc
            .perform(
                get("/products")
                    .param("category", category)
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().`is`(200))
    }
}