package com.mytheresa.challenge

import com.mytheresa.challenge.utils.JsonUtils
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import javax.annotation.PostConstruct

@SpringBootApplication
class ChallengeApplication() {

	val logger = LoggerFactory.getLogger(this::class.java)

	@PostConstruct
	fun postConstruct() {
		logger.info("Saving products from initial json file")
		val listOfProducts = JsonUtils("initial-products.json").getListOfProducts()
		println("test")
		//json.json_field["products"].toList()
	}
}

fun main(args: Array<String>) {
	runApplication<ChallengeApplication>(*args)
}

