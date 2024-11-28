package com.AH.delivery.service

import com.AH.delivery.datamodel.Delivery
import com.AH.delivery.datamodel.DeliveryStatus
import com.AH.delivery.dto.BulkDeliveryUpdateRequest
import com.AH.delivery.dto.DeliveryUpdateRequest
import com.AH.delivery.dto.DeliverySummary
import com.AH.delivery.repo.DeliveryRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.ZonedDateTime
import java.util.*

@Service
open class DeliveryService(private val deliveryrepo: DeliveryRepository) {

    @Transactional
    open fun save(delivery: Delivery): Delivery {
        if (delivery.status != DeliveryStatus.IN_PROGRESS && delivery.status != DeliveryStatus.DELIVERED) {
            throw IllegalArgumentException("Status must be IN_PROGRESS or DELIVERED")
        } else {
            return deliveryrepo.save(delivery)
        }
    }

    @Transactional
    open fun updateDelivery(id: UUID, request: DeliveryUpdateRequest): Delivery {
        val delivery = deliveryrepo.findById(id).orElseThrow { IllegalArgumentException("ID $id does not exist") }
        if (request.status == DeliveryStatus.DELIVERED && request.finishedAt == null){
            throw IllegalArgumentException("Finished time must be provided for delivered goods")
        } else {
            delivery.status = request.status
            delivery.finishedAt = request.finishedAt
            return deliveryrepo.save(delivery)
        }
    }

    @Transactional
    open fun bulkupdateDelivery(requests: List<BulkDeliveryUpdateRequest>): List<Delivery> {
        val responses = mutableListOf<Delivery>()
        for (request in requests) {
            val id = request.id
            val delivery = deliveryrepo.findById(id).orElseThrow {IllegalArgumentException("ID $id does not exist")}
            if (request.status == DeliveryStatus.DELIVERED && request.finishedAt == null){
                throw IllegalArgumentException("Finished time must be provided for delivered goods")
            } else {
                delivery.status = request.status
                delivery.finishedAt = request.finishedAt
                responses.add(deliveryrepo.save(delivery))
            }
        }
        return responses
    }

    @Transactional
    open fun summaryDelivery(): DeliverySummary {
        val now = ZonedDateTime.now()
        val zone = now.zone
        val yesterdayStart = now.minusDays(1).toLocalDate().atStartOfDay(zone)
        val todayStart = now.toLocalDate().atStartOfDay(zone)
        val countdeliveries = deliveryrepo.countByStartDateAfter(yesterdayStart, todayStart)
        val deliveries = deliveryrepo.findByStartDateAfter(yesterdayStart, todayStart)
        val maxstartedAt = deliveries.maxOfOrNull { it.startedAt }
        val minstartedAt = deliveries.minOfOrNull { it.startedAt }
        // if there was only one or no delivery yesterday, then the average minutes between
        // orders should return as null
        val diffminutes = if(countdeliveries > 1 && maxstartedAt != null && minstartedAt != null){
            Duration.between(minstartedAt, maxstartedAt).toMinutes()
        } else {
            null
        }
        val averageMinutesBetweenDeliveryStart = diffminutes?.div(countdeliveries - 1)
        return DeliverySummary(countdeliveries, averageMinutesBetweenDeliveryStart)
    }
}