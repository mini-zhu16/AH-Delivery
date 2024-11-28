package com.AH.delivery

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class DeliveryApplication

fun man(args: Array<String>) {
    runApplication<DeliveryApplication>(*args)
}