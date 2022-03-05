package com.mytheresa.challenge.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.mytheresa.challenge.enums.CurrencyEnum

class PriceDTO (
    @JsonProperty("original")
    val original: Int,
    @JsonProperty("final")
    val final: Int,
    @JsonInclude(JsonInclude.Include.ALWAYS)
    @JsonProperty("discount_percentage")
    val discountPercentage: String? = null,
    @JsonProperty("currency")
    val currency: CurrencyEnum = CurrencyEnum.EUR,
)