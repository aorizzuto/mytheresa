package com.mytheresa.challenge.service

import com.mytheresa.challenge.enums.CurrencyEnum
import com.mytheresa.challenge.exceptions.BadRequestException
import com.mytheresa.challenge.exceptions.ErrorCode
import com.mytheresa.challenge.repository.IProductRepository
import com.mytheresa.challenge.repository.model.Product
import com.mytheresa.challenge.service.impl.DiscountServiceImpl
import com.mytheresa.challenge.service.impl.ProductServiceImpl
import com.mytheresa.challenge.utils.JsonUtils
import com.mytheresa.challenge.utils.MockitoUtils.anyObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.*
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.doCallRealMethod
import java.math.BigDecimal


class ProductServiceTests {

    private lateinit var productService: ProductServiceImpl

    @Spy
    private lateinit var discountService: DiscountServiceImpl

    // These are two ways to Mock with Mockito. With @Mock or the following way
    private val productRepository: IProductRepository = Mockito.mock(IProductRepository::class.java)

    @Captor
    lateinit var categoryCaptor: ArgumentCaptor<String>

    val maxRecords = 5

    @BeforeEach
    fun setup() {
        MockitoAnnotations.initMocks(this)
        productService = ProductServiceImpl(productRepository, discountService, maxRecords)
    }

    @ParameterizedTest
    @ValueSource(strings = ["Boots", "Sandals", "Hats"])
    fun `GIVEN a request to validate WHEN all parameters exists THEN success`(category: String){
        assertDoesNotThrow { productService.validate(category, BigDecimal.TEN) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["Boots", "Sandals", "Hats"])
    fun `GIVEN a request to validate WHEN only category field exist THEN success`(category: String){
        assertDoesNotThrow { productService.validate(category, null) }
    }

    @Test
    fun `GIVEN a request to validate WHEN category exists but priceLowerThan is negative THEN exception`(){
        val exc = assertThrows<BadRequestException> { productService.validate("Boots", BigDecimal(-10)) }
        assert(exc.errorCode == ErrorCode.PRICE_NEGATIVE)
    }

    @ParameterizedTest
    @ValueSource(strings = ["Sandals"])
    fun `GIVEN a request to process WHEN products exist but no discount are applied THEN respond success`(category: String){
        getMockitos()

        val response = assertDoesNotThrow { productService.process(category, null) }

        assert(response.size == testDatabase(category).size) // Check number of products returned
        assert(response.all { product -> product.price.original == product.price.final }) // Check there are no discount
        assert(response.all { product -> product.price.discountPercentage == null }) // Check there are no discount
        assert(response.all { product -> product.category.equals(category, ignoreCase = true) }) // Check category in final response
    }

    @ParameterizedTest
    @ValueSource(strings = ["Sandals"])
    fun `GIVEN a request to process WHEN products exist but priceLessThan is 1 THEN respond empty`(category: String){
        getMockitos()

        val response = assertDoesNotThrow { productService.process(category, BigDecimal(1)) }

        assert(response.isEmpty()) // Check number of products returned
    }

    @ParameterizedTest
    @ValueSource(strings = ["Boots"])
    fun `GIVEN a request to process WHEN products exist and discount are applied THEN respond success`(category: String){
        getMockitos()

        val response = assertDoesNotThrow { productService.process(category, BigDecimal(1000)) }

        assert(response.size == testDatabase(category).size) // Check number of products returned
        assert(response.all { product -> product.price.original > product.price.final }) // Check there are discount
        assert(response.all { product -> product.price.discountPercentage != null }) // Check there are discount
        assert(response.all { product -> product.category.equals(category, ignoreCase = true) }) // Check category in final response
        assert(response.all { product -> product.price.currency == CurrencyEnum.EUR }) // Check category in final response
    }

    @Test
    fun `GIVEN a request to process WHEN products no exists THEN response empty`(){
        getMockitos()

        val category = "no product"
        val response = assertDoesNotThrow { productService.process(category, BigDecimal(100)) }

        assert(response.isEmpty()) // Check number of products returned
        Mockito.verify(discountService, Mockito.times(0)).applySKU3discount(anyObject()) // Check number of calls to mock
    }

    private fun testDatabase(categoryFilter: String, priceFilter: Int? = null): List<Product> {
            return JsonUtils("initial-products.json")
                .getListOfProducts()
                .filter { it.category.equals(categoryFilter, ignoreCase = true) }
                .filter {
                    if (priceFilter != null) {
                        it.price.toInt() <= priceFilter!!
                    } else {
                        true
                    }
                }
    }


    private fun getMockitos() {
        `when`(productRepository.findProductsByCategoryAndPrice(anyString(), anyInt(), anyObject()))
            .thenAnswer { testDatabase(it.getArgument(0), it.getArgument(1)) }
        `when`(productRepository.findByCategory(anyString(), anyObject()))
            .thenAnswer { testDatabase(it.getArgument(0), null) }
        doCallRealMethod().`when`(discountService).applySKU3discount(anyObject())
        doCallRealMethod().`when`(discountService).applyCategoryDiscount(anyObject(), anyObject())
    }
}