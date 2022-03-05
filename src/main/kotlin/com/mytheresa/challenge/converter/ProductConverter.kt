package com.mytheresa.challenge.converter

import com.mytheresa.challenge.dto.PriceDTO
import com.mytheresa.challenge.dto.ProductDTO
import com.mytheresa.challenge.dto.ProductResponseDTO
import com.mytheresa.challenge.repository.model.Product
import java.math.BigDecimal

object ProductConverter {

    fun toResponse(products: List<ProductDTO>) =
        products.map { product ->
            with(product) {
                ProductResponseDTO(
                    sku = sku,
                    name = name,
                    category = category,
                    price = PriceDTO(
                        original = price.toInt(),
                        final = discount?.let { getPriceWithDiscount(price, it) } ?: price.toInt(),
                        discountPercentage = product.discount?.let { product.discount.toString() + "%"},
                    )
                )
            }
        }

    fun toProductDTO(products: List<Product>) =
        products.map { product ->
            with(product) {
                ProductDTO(
                    sku = sku,
                    name = name,
                    category = category,
                    price = price.toBigDecimal()
                )
            }
        }

    private fun getPriceWithDiscount(price: BigDecimal, discount: BigDecimal): Int {
        return price.multiply(BigDecimal(1).minus(discount.divide(BigDecimal(100)))).toInt()
    }
}