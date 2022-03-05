package com.mytheresa.challenge.controller

import com.mytheresa.challenge.dto.ProductResponseDTO
import com.mytheresa.challenge.service.impl.ProductServiceImpl
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
class ProductsController(val productService: ProductServiceImpl) {

    @GetMapping("/products")
    fun getProducts(
        @RequestParam category: String,
        @RequestParam(required = false, name = "price_less_than") priceLessThan: BigDecimal?
    ): List<ProductResponseDTO> {
        productService.validate(category, priceLessThan)
        return productService.process(category, priceLessThan)
    }
}