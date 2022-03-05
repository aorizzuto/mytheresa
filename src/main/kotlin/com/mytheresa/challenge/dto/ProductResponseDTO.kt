package com.mytheresa.challenge.dto

import com.fasterxml.jackson.annotation.JsonProperty

class ProductResponseDTO (
    @JsonProperty("sku")
    val sku: String,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("category")
    val category: String,
    @JsonProperty("price")
    val price: PriceDTO,
)
