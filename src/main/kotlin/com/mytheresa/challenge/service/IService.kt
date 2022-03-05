package com.mytheresa.challenge.service

import com.mytheresa.challenge.dto.ProductResponseDTO
import java.math.BigDecimal

interface IService {
    fun validate(category: String, priceLessThan: BigDecimal?)
    fun process(category: String, priceLessThan: BigDecimal?): List<ProductResponseDTO>
}