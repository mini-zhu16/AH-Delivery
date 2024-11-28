package com.AH.delivery.controller

import com.AH.delivery.service.DeliveryService
import com.AH.delivery.datamodel.Delivery
import com.AH.delivery.dto.DeliveryUpdateRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/deliveries")
class DeliveryController(private val deliveryservice: DeliveryService) {
    @PostMapping
    fun createDelivery(@RequestBody delivery: Delivery): Delivery = deliveryservice.save(delivery)

    @PatchMapping("/{id}")
    fun updateDelivery(
        @PathVariable id: UUID,
        @RequestBody request: DeliveryUpdateRequest): ResponseEntity<Delivery> {
        val updatedDelivery = deliveryservice.updateDelivery(id, request)
        return ResponseEntity.ok(updatedDelivery)
    }
}