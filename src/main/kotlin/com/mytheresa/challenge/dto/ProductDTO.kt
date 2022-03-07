package com.mytheresa.challenge.dto

import java.math.BigDecimal

class ProductDTO (
    val sku: String,
    val name: String,
    val category: String,
    val price: BigDecimal,
    var discounts: MutableList<BigDecimal> = mutableListOf()
)