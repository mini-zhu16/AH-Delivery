package com.AH.delivery.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.AH.delivery.datamodel.Delivery
import java.util.*


@Repository
interface DeliveryRepository : JpaRepository<Delivery, UUID>