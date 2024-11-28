package com.AH.delivery.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import com.AH.delivery.datamodel.Delivery
import java.time.LocalDateTime
import java.util.*


@Repository
interface DeliveryRepository : JpaRepository<Delivery, UUID>{
    @Query ("""SELECT count(d) FROM Delivery d WHERE d.startedAt > :start""")
    fun countByStartDateAfter(
        @Param("start") start: LocalDateTime
    ): Int

    @Query ("""SELECT d FROM Delivery d WHERE d.startedAt > :start""")
    fun findByStartDateAfter(
        @Param("start") start: LocalDateTime
    ): List<Delivery>
}

