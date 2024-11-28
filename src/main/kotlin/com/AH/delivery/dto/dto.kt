package com.AH.delivery.dto

import com.AH.delivery.datamodel.DeliveryStatus
import org.jetbrains.annotations.NotNull
import java.time.ZonedDateTime
import java.util.*

data class DeliveryUpdateRequest(
    @field:NotNull("Status needs to be defined")
    val status: DeliveryStatus,

    val finishedAt: ZonedDateTime?= null
)

data class BulkDeliveryUpdateRequest(
    @field:NotNull("Id should be provided")
    val id: UUID,

    @field:NotNull("Status needs to be defined")
    val status: DeliveryStatus,

    val finishedAt: ZonedDateTime?= null
)

data class DeliverySummary(
    @field: NotNull
    val summary: Int,

    @field: NotNull
    val averageMinutesBetweenDeliveryStart: Long?
)