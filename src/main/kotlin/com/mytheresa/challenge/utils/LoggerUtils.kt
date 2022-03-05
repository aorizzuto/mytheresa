package com.mytheresa.challenge.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class LoggerUtils(val title: String) {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun log(text: String){
        logger.info("$title - $text")
    }

}