package com.mytheresa.challenge.repository

import com.mytheresa.challenge.repository.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IProductRepository: JpaRepository<Product, String> {
}