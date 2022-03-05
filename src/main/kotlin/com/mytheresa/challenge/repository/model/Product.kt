package com.mytheresa.challenge.repository.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "products")
class Product (
    @Id
    val sku: String,
    @Column
    val name: String,
    @Column
    val category: String,
    @Column
    val price: String
) : java.io.Serializable {
    constructor() : this("", "", "", "")
}