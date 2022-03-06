package com.mytheresa.challenge.utils

import org.mockito.ArgumentCaptor
import org.mockito.Mockito

object MockitoUtils {
    fun <T> anyObject(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T

    fun <T> capture(captor: ArgumentCaptor<T>): T = captor.capture()
}