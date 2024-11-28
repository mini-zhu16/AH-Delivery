package com.AH.delivery.datamodel

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.validation.constraints.NotNull
import java.time.ZonedDateTime
import java.util.UUID

enum class DeliveryStatus{IN_PROGRESS, DELIVERED}

@Entity
data class Delivery(
    @Id val id: UUID = UUID.randomUUID(),
    @field: NotNull val vehicleId: String = "",
    @field: NotNull val startedAt: ZonedDateTime = ZonedDateTime.now(),
    var finishedAt: ZonedDateTime? = null,
    @Enumerated(EnumType.STRING)
    @field:NotNull var status: DeliveryStatus = DeliveryStatus.IN_PROGRESS
)