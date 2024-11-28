package com.AH.delivery.controller

import com.AH.delivery.service.DeliveryService
import com.AH.delivery.datamodel.Delivery
import com.AH.delivery.dto.BulkDeliveryUpdateRequest
import com.AH.delivery.dto.DeliverySummary
import com.AH.delivery.dto.DeliveryUpdateRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/deliveries")
class DeliveryController(private val deliveryservice: DeliveryService) {
    @PostMapping
    fun createDelivery(@Valid @RequestBody delivery: Delivery): ResponseEntity<Delivery>{
        val createDelivery = deliveryservice.save(delivery)
        return ResponseEntity.ok(createDelivery)
    }

    @PatchMapping("/{id}")
    fun updateDelivery(
        @PathVariable id: UUID,
        @RequestBody request: DeliveryUpdateRequest): ResponseEntity<Delivery> {
        val updatedDelivery = deliveryservice.updateDelivery(id, request)
        return ResponseEntity.ok(updatedDelivery)
    }

    @PatchMapping("/bulk-update")
    fun bulkupdateDelivery(@RequestBody requests: List<BulkDeliveryUpdateRequest>): ResponseEntity<Map<String, List<Delivery>>>  {
        val bulkupdateDelivery = deliveryservice.bulkupdateDelivery(requests)
        return ResponseEntity.ok(mapOf("deliveries" to bulkupdateDelivery))
    }

    @GetMapping("/business-summary")
    fun summaryDelivery(): ResponseEntity<DeliverySummary> {
        val summaryDelivery = deliveryservice.summaryDelivery()
        return ResponseEntity.ok(summaryDelivery)
    }
}