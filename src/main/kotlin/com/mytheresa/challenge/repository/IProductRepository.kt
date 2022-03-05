package com.mytheresa.challenge.repository

import com.mytheresa.challenge.repository.model.Product
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface IProductRepository: JpaRepository<Product, String> {

    @Query(
        "select * from products where category=:category and price <= :priceLessThan",
        nativeQuery = true
    )
    fun findProductsByCategoryAndPrice(category: String, priceLessThan: Int, pageable: Pageable): List<Product>

    fun findByCategory(category: String, pageable: Pageable): List<Product>
}