package com.mytheresa.challenge.repository.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "products")
class Product (
    @Id
    val sku: String,
    val name: String,
    val category: String,
    val price: String
)