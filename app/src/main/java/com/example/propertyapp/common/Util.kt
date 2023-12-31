package com.example.propertyapp.common

import com.example.propertyapp.data.remote.dto.PropertyDto
import com.example.propertyapp.domain.model.PropertyEntity
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun PropertyDto.asEntity(): List<PropertyEntity> =
    data.map {
        PropertyEntity(
            id = it.id.toInt(),
            agentName = "${it.agent.first_name} ${it.agent.last_name}",
            agentAvatar = it.agent.avatar.medium?.url ?: "",
            agentCompany = it.agent.company_name,
            propertyAddress = it.location?.address ?: "",
            bedroomCount = it.bedrooms ?: 0,
            bathroomCount = it.bathrooms ?: 0,
            carspaceCount = it.carspaces ?: 0,
            propertyImage = it.property_images?.first()?.attachment?.url ?: "",
            propertyPrice = it.display_price ?: "",
            propertyType = it.property_type ?: "",
            auctionDate = it.auction_date?.toReadableDate() ?: "unknown",
            description = it.description
        )
    }

fun String.toReadableDate(): String? = try {
    ZonedDateTime.parse(this).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
} catch (e: Exception) {
    null
}