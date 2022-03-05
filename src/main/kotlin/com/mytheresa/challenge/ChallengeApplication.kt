package com.mytheresa.challenge

import com.mytheresa.challenge.repository.IProductRepository
import com.mytheresa.challenge.utils.JsonUtils
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener
import javax.annotation.PostConstruct

@SpringBootApplication
class ChallengeApplication(
	val productRepository: IProductRepository
) {

	val logger = LoggerFactory.getLogger(this::class.java)

	@EventListener(ApplicationReadyEvent::class)
	fun afterStartUp() {
		logger.info("Saving products from initial json file")
		val listOfProducts = JsonUtils("initial-products.json").getListOfProducts()
		productRepository.saveAll(listOfProducts)
	}
}

fun main(args: Array<String>) {
	runApplication<ChallengeApplication>(*args)
}

