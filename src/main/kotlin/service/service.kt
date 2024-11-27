package com.AH.delivery.service

import com.AH.delivery.datamodel.Delivery
import com.AH.delivery.datamodel.DeliveryStatus
import com.AH.delivery.repo.DeliveryRepository
import org.springframework.stereotype.Service

@Service
class DeliveryService(private val deliveryrepo: DeliveryRepository) {
    fun save(delivery: Delivery): Delivery {
        if (delivery.status != DeliveryStatus.IN_PROGRESS && delivery.status != DeliveryStatus.DELIVERED) {
            throw IllegalArgumentException("Status must be IN_PROGRESS or DELIVERED")
        } else {
            return deliveryrepo.save(delivery)
        }
    }
}