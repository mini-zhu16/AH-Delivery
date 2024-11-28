package com.AH.delivery.datamodel

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime
import java.util.UUID

enum class DeliveryStatus{IN_PROGRESS, DELIVERED}

@Entity
data class Delivery(
    @Id val id: UUID = UUID.randomUUID(),
    @field: NotNull val vehicleId: String,
    @field: NotNull val startedAt: LocalDateTime = LocalDateTime.now(),
    var finishedAt: LocalDateTime? = null,
    @Enumerated(EnumType.STRING)
    @field:NotNull var status: DeliveryStatus = DeliveryStatus.IN_PROGRESS
)