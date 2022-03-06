package com.mytheresa.challenge.service

import com.mytheresa.challenge.converter.ProductConverter
import com.mytheresa.challenge.dto.ProductDTO
import com.mytheresa.challenge.enums.CategoryEnum
import com.mytheresa.challenge.service.impl.DiscountServiceImpl
import com.mytheresa.challenge.utils.JsonUtils
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class DiscountServiceTest {

    lateinit var discountService: DiscountServiceImpl

    @BeforeEach
    fun setup() {
        discountService = DiscountServiceImpl()
    }

    @Test
    fun `GIVEN product with SKU=3 WHEN applied SKU=3 discount THEN applied`() {
        val products = getProducts("Boots")

        assertDoesNotThrow { discountService.applySKU3discount(products) }

        assert(products.first { product -> product.sku.toInt() == 3 }.discount != null)
    }

    @Test
    fun `GIVEN product with SKU!=3 WHEN applied SKU=3 discount THEN not applied`() {
        val products = getProducts("Sandals")

        assertDoesNotThrow { discountService.applySKU3discount(products) }

        assert(products.all { product -> product.discount == null })
    }

    @Test
    fun `GIVEN product with category discount WHEN applied category discount THEN applied`() {
        val products = getProducts("Boots")
        val category = CategoryEnum.BOOTS

        assertDoesNotThrow { discountService.applyCategoryDiscount(products, category) }
        assert(products.all { product -> product.discount != null && product.discount == category.categoryDiscount })
    }

    @Test
    fun `GIVEN product without category discount WHEN applied category discount THEN applied`() {
        val products = getProducts("Sneakers")
        val category = CategoryEnum.BOOTS

        assertDoesNotThrow { discountService.applyCategoryDiscount(products, category) }
        assert(products.all { product -> product.discount == null })
    }

    @Test
    fun `GIVEN product with SKU!=3 and not category discount WHEN applied both discounts THEN apply none`() {
        val products = getProducts("Sneakers")
        val category = CategoryEnum.BOOTS

        assertDoesNotThrow { discountService.applySKU3discount(products) }
        assertDoesNotThrow { discountService.applyCategoryDiscount(products, category) }
        assert(products.all { product -> product.discount == null })
        assert(products.all { product -> product.sku.toInt() != 3})
    }

    @Test
    fun `GIVEN product with SKU=3 and category discount WHEN applied both discounts THEN apply greater one`() {
        val products = getProducts("Boots")
        val category = CategoryEnum.BOOTS

        assertDoesNotThrow { discountService.applySKU3discount(products) }
        assertDoesNotThrow { discountService.applyCategoryDiscount(products, category) }
        assert(products.first { product -> product.sku.toInt() == 3 }.discount == CategoryEnum.BOOTS.categoryDiscount)
    }

    private fun getProducts(categoryFilter: String): List<ProductDTO> {
        return JsonUtils("initial-products.json")
            .getListOfProducts()
            .filter { it.category.equals(categoryFilter, ignoreCase = true) }
            .run { ProductConverter.toProductDTO(this) }
    }
}