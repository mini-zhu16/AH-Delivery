package com.AH.delivery.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.AH.delivery.service.DeliveryService
import com.AH.delivery.datamodel.Delivery
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@RestController
@RequestMapping("/deliveries")
class DeliveryController(private val deliveryservice: DeliveryService) {
    @PostMapping
    fun createDelivery(@RequestBody delivery: Delivery): Delivery = deliveryservice.save(delivery)
}