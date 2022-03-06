package com.mytheresa.challenge.utils

import org.springframework.core.env.Environment

class EnvironmentUtils (private val env: Environment) {

    fun isLocal() = env.activeProfiles.contains("local")
    fun isDev() = env.activeProfiles.contains("dev")
    fun isQa() = env.activeProfiles.contains("qa")
    fun isProd() = env.activeProfiles.contains("prod")

    fun getCurrentEnvironment() =
        when {
            isLocal() -> MytheresaEnvironment.LOCAL
            isDev() -> MytheresaEnvironment.DEV
            isQa() -> MytheresaEnvironment.QA
            isProd() -> MytheresaEnvironment.PROD
            else -> MytheresaEnvironment.LOCAL
        }
}

enum class MytheresaEnvironment {
    LOCAL, DEV, QA, PROD
}