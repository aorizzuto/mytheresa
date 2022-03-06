package com.mytheresa.challenge.service.impl

import com.mytheresa.challenge.converter.ProductConverter
import com.mytheresa.challenge.dto.ProductDTO
import com.mytheresa.challenge.dto.ProductResponseDTO
import com.mytheresa.challenge.enums.CategoryEnum
import com.mytheresa.challenge.exceptions.BadRequestException
import com.mytheresa.challenge.exceptions.ErrorCode
import com.mytheresa.challenge.repository.IProductRepository
import com.mytheresa.challenge.repository.model.Product
import com.mytheresa.challenge.service.IService
import com.mytheresa.challenge.utils.JsonUtils
import com.mytheresa.challenge.utils.LoggerUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class ProductServiceImpl(
    val productRepository: IProductRepository,
    val discountService: DiscountServiceImpl,
    @Value("\${response.max-records:}") val maxRecords: Int
): IService, LoggerUtils("[PRODUCT-SERVICE]") {

    override fun validate(category: String, priceLessThan: BigDecimal?){
        log("Start request validation with category: $category and priceLessThan: $priceLessThan")

        priceLessThan?.let{
            if (priceLessThan <= BigDecimal.ZERO) {
                log("Validation Failed")
                throw BadRequestException(ErrorCode.PRICE_NEGATIVE)
            }
        }

        log("Validation Success")
    }

    override fun process(category: String, priceLessThan: BigDecimal?): List<ProductResponseDTO> {
        log("Start process")

        val products = getProducts(category, priceLessThan)

        log("Products returned from database are: ${JsonUtils().getObjectAsString(products)}")

        if (products.isNotEmpty()) {
            applyDiscounts(products)
            log("Products after discounts returned are: ${JsonUtils().getObjectAsString(products)}")
        }

        return ProductConverter.toResponse(products)
    }

    private fun applyDiscounts(products: List<ProductDTO>) {
        discountService.applySKU3discount(products)
        discountService.applyCategoryDiscount(products, CategoryEnum.BOOTS)
    }

    private fun getProducts(category: String, priceLessThan: BigDecimal?): List<ProductDTO> {
        log("Getting products from database")
        val products = getProductsFromDatabase(category, priceLessThan)
        return ProductConverter.toProductDTO(products)
    }

    private fun getProductsFromDatabase(category: String, priceLessThan: BigDecimal?): List<Product> {
        val pageable: Pageable = PageRequest.of(0, maxRecords)

        return if (priceLessThan == null){
            productRepository.findByCategory(category, pageable)
        } else {
            val finalPrice = getPriceAsInt(priceLessThan)
            productRepository.findProductsByCategoryAndPrice(category, finalPrice, pageable)
        }
    }

    private fun getPriceAsInt(price: BigDecimal) = price.multiply(BigDecimal(100)).toInt()

}