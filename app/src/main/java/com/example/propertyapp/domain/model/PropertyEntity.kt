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
    // For Composable previews
    companion object {
        val DEFAULT = PropertyEntity(
            id = 0,
            agentName = "Agent Name",
            agentAvatar = "",
            agentCompany = "Agent Company",
            propertyAddress = "100 Property Address, Address, 1000",
            bedroomCount = 2,
            bathroomCount = 1,
            carspaceCount = 1,
            propertyImage = "",
            propertyPrice = "$900000",
            propertyType = "Type",
            auctionDate = "23/10/2020",
            description = "Description"
        )
    }
}
