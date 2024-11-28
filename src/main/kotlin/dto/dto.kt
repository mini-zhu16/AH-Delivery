package com.AH.delivery.dto

import com.AH.delivery.datamodel.DeliveryStatus
import com.fasterxml.jackson.annotation.JsonFormat
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime

data class DeliveryUpdateRequest(
    @field:NotNull("Status needs to be defined")
    val status: DeliveryStatus,

    @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    val finishedAt: LocalDateTime?= null
)