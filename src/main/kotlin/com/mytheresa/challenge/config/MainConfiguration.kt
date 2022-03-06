package com.mytheresa.challenge.config

import com.mytheresa.challenge.utils.EnvironmentUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

@Configuration
class MainConfiguration {

    @Bean
    fun environmentUtils(environment: Environment) = EnvironmentUtils(environment)
}