package com.mytheresa.challenge.exceptions

import org.springframework.http.HttpStatus

enum class ErrorCode (val msg: String, val errorCode: HttpStatus) {

    PRICE_NEGATIVE("Price is lower than zero", HttpStatus.BAD_REQUEST),
    BAD_REQUEST("There was something missing in the request", HttpStatus.BAD_REQUEST)
}