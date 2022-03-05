package com.mytheresa.challenge.service

import com.mytheresa.challenge.dto.ProductDTO
import com.mytheresa.challenge.enums.CategoryEnum

interface IDiscountService {

    fun applyCategoryDiscount(products: List<ProductDTO>, category: CategoryEnum)

    fun applySKU3discount(products: List<ProductDTO>)

}