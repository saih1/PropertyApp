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
            propertyAddress = "123 Riverside Avenue, Riverview Heights, City town, Country land",
            bedroomCount = 2,
            bathroomCount = 1,
            carspaceCount = 1,
            propertyImage = "",
            propertyPrice = "$900000",
            propertyType = "Apartment",
            auctionDate = "23/10/2020",
            description = "Welcome to your dream riverside retreat! This exquisite apartment," +
                    " nestled along the tranquil banks of the scenic River Serene," +
                    " offers a perfect blend of modern luxury and natural beauty." +
                    " Situated in the heart of the vibrant city, this is the ultimate" +
                    " urban oasis for those seeking a sophisticated and serene lifestyle.\n"
        )
    }
}
