package com.mytheresa.challenge.utils

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.mytheresa.challenge.repository.model.Product
import org.springframework.core.io.ClassPathResource
import java.nio.file.Files

class JsonUtils(private val path: String = "") {
    private val mapper = jacksonObjectMapper()
    private var json: JsonNode? = null

    private fun loadFile() = apply {
        val file = Files.readString(ClassPathResource(path).file.toPath())
        json = mapper.readTree(file)
    }

    private fun responseBody(case: String) = json!!.get(case).toString()

    private fun toListOfProducts() : List<Product> {
        return mapper.readValue(responseBody("products"), object : TypeReference<List<Product>>() {}) // PROBAR!
    }

    fun getListOfProducts(): List<Product> = loadFile().toListOfProducts()

    fun <T> getObjectAsString(obj: T): String = mapper.writeValueAsString(obj)
}