package com.mytheresa.challenge.config

import com.mytheresa.challenge.repository.IProductRepository
import com.mytheresa.challenge.utils.EnvironmentUtils
import com.mytheresa.challenge.utils.JsonUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class InitConfig(
    private val productRepository: IProductRepository,
    private val environmentUtils: EnvironmentUtils
    ) {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @EventListener(ApplicationReadyEvent::class)
    fun afterStartUp() {
        if (environmentUtils.isLocal()) {
            logger.info("Saving products from initial json file")
            val listOfProducts = JsonUtils("initial-products.json").getListOfProducts()
            productRepository.saveAll(listOfProducts)
        }
    }
}