package com.mytheresa.challenge.exceptions

import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseEntityBody(val message: String, @JsonProperty("error_code") val errorCode: String) {
    constructor(errorCode: ErrorCode) : this(errorCode.msg, errorCode.name)
}