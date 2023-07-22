package com.example.propertyapp.domain.model

data class PropertyEntity(
    val id: Int,
    val agentName: String,
    val agentAvatar: String,
    val agentCompany: String,
    val propertyAddress: String,
    val bedroomCount: Int,
    val bathroomCount: Int,
    val carspaceCount: Int,
    val propertyImage: String,
    val propertyPrice: String,
    val propertyType: String,
    val auctionDate: String,
    val description: String
) {
    // TODO: Remove after testing
    companion object {
        val DEFAULT = PropertyEntity(
            id = 0,
            agentName = "John Smith",
            agentAvatar = "",
            agentCompany = "Ray White",
            propertyAddress = "",
            bedroomCount = 2,
            bathroomCount = 1,
            carspaceCount = 1,
            propertyImage = "",
            propertyPrice = "",
            propertyType = "",
            auctionDate = "",
            description = ""
        )
    }
}
