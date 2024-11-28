package com.AH.delivery.service

import com.AH.delivery.datamodel.Delivery
import com.AH.delivery.datamodel.DeliveryStatus
import com.AH.delivery.dto.DeliveryUpdateRequest
import com.AH.delivery.repo.DeliveryRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class DeliveryService(private val deliveryrepo: DeliveryRepository) {
    fun save(delivery: Delivery): Delivery {
        if (delivery.status != DeliveryStatus.IN_PROGRESS && delivery.status != DeliveryStatus.DELIVERED) {
            throw IllegalArgumentException("Status must be IN_PROGRESS or DELIVERED")
        } else {
            return deliveryrepo.save(delivery)
        }
    }

    fun updateDelivery(id: UUID, request: DeliveryUpdateRequest): Delivery {
        val delivery = deliveryrepo.findById(id).orElseThrow { IllegalArgumentException("ID $id does not exist") }
        if (request.status == DeliveryStatus.DELIVERED && request.finishedAt == null){
            throw IllegalArgumentException("Finished time must be provided for delivered goods")
        } else {
            delivery.status = request.status
            delivery.finishedAt = request.finishedAt
            return deliveryrepo.save(delivery)
        }
    }
}