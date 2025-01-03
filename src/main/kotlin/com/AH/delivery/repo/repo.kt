package com.AH.delivery.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import com.AH.delivery.datamodel.Delivery
import java.time.ZonedDateTime
import java.util.*


@Repository
interface DeliveryRepository : JpaRepository<Delivery, UUID>{
    @Query ("""SELECT count(d) FROM Delivery d WHERE d.startedAt > :yesterdaystart AND d.startedAt < :todaystart""")
    fun countByStartDateAfter(
        @Param("yesterdaystart") yesterdaystart: ZonedDateTime,
        @Param("todaystart") todaystart: ZonedDateTime
    ): Int

    @Query ("""SELECT d FROM Delivery d WHERE d.startedAt > :yesterdaystart AND d.startedAt < :todaystart""")
    fun findByStartDateAfter(
        @Param("yesterdaystart") yesterdaystart: ZonedDateTime,
        @Param("todaystart") todaystart: ZonedDateTime
    ): List<Delivery>
}

