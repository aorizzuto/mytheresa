package com.mytheresa.challenge.enums

import java.math.BigDecimal

enum class CategoryEnum(val value: String, val categoryDiscount: BigDecimal = BigDecimal(0)) {
    BOOTS("BOOTS", BigDecimal(30)), // has discount
    SANDALS("SANDALS") // does not have any discount
}