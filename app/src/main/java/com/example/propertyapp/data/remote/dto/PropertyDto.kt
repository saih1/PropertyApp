package com.example.propertyapp.data.remote.dto

data class PropertyDto(
    val data: List<Property>
)

data class Property(
    val id: String,
    val auction_date: String?,
    val available_from: String?,
    val bedrooms: Int?,
    val bathrooms: Int?,
    val carspaces: Int?,
    val date_first_listed: String?,
    val date_updated: String?,
    val description: String,
    val display_price: String?,
    val currency: String?,
    val location: Location?,
    val owner: Owner?,
    val property_images: List<PropertyImage>?,
    val agent: Agent,
    val property_type: String?,
    val sale_type: String?
)

data class Location(
    val address: String?,
    val state: String?,
    val suburb: String?,
    val postcode: String?,
    val latitude: Double?,
    val longitude: Double?
)

data class Owner(
    val first_name: String?,
    val last_name: String?,
    val dob: String?,
    val avatar: Avatar?
)

data class Avatar(
    val small: Image?,
    val medium: Image?,
    val large: Image?
)

data class Image(
    val url: String?
)

data class PropertyImage(
    val id: Int,
    val attachment: Image
)

data class Agent(
    val first_name: String,
    val last_name: String,
    val company_name: String,
    val avatar: Avatar
)