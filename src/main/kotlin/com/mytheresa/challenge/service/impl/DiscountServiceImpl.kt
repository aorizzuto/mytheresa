package com.mytheresa.challenge.service.impl

import com.mytheresa.challenge.dto.ProductDTO
import com.mytheresa.challenge.enums.CategoryEnum
import com.mytheresa.challenge.service.IDiscountService
import com.mytheresa.challenge.utils.LoggerUtils
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class DiscountServiceImpl: LoggerUtils("[DISCOUNT-SERVICE]"), IDiscountService {

    override fun applyCategoryDiscount(products: List<ProductDTO>, category: CategoryEnum) {
        log("Applying discount for category")
        applyDiscount(products, category.categoryDiscount) {product -> product.category.equals(category.value, ignoreCase = true)}
    }

    override fun applySKU3discount(products: List<ProductDTO>) {
        log("Applying SKU=3 discount")
        applyDiscount(products, BigDecimal(15)) {product -> product.sku == "000003"}
    }

    private fun applyDiscount(products: List<ProductDTO>, discountToApply: BigDecimal, predicate: (ProductDTO) -> (Boolean)) {
        products.filter { predicate(it) }
            .forEach { product ->
                with(product) {
                    log("Applying discount to sku: $sku with name: $name and category: $category")
                    discount = discountToApply
                }
            }
    }
}