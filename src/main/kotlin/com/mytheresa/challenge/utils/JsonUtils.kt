package com.mytheresa.challenge.utils

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.mytheresa.challenge.config.MainConfiguration
import com.mytheresa.challenge.repository.model.Product
import org.springframework.core.io.ClassPathResource
import java.nio.file.Files

class JsonUtils(private val path: String) {
    private val mapper = jacksonObjectMapper()
    private var json: JsonNode? = null

    private fun loadFile() = apply {
        val file = Files.readString(ClassPathResource(path).file.toPath())
        json = mapper.readTree(file)
    }

    private fun responseBody(case: String) = json!!.get(case).toString()

    private fun <T> toObject(case: String, clazz: Class<T>) : List<Product> {
        return mapper.readValue(responseBody(case), object : TypeReference<List<Product>>() {}) // PROBAR!
    }

    fun getListOfProducts(): List<Product> {
        val json = loadFile()
        json.toObject("products", Product::class.java)

        return listOf()
    }
}