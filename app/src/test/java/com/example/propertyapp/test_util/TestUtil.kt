package com.example.propertyapp.test_util

import com.example.propertyapp.data.remote.dto.*

val fakePropertyDto = PropertyDto(
    data = listOf(
        Property(
            id = "12345",
            auction_date = "2023-08-15T14:00:00+10:00",
            available_from = "2023-08-20T00:00:00+10:00",
            bedrooms = 3,
            bathrooms = 2,
            carspaces = 1,
            date_first_listed = "2023-08-01T09:00:00+10:00",
            date_updated = "2023-08-10T15:30:00+10:00",
            description = "This is a beautiful property.",
            display_price = "$500,000",
            currency = "AUD",
            location = Location(
                address = "123 Main Street",
                state = "NSW",
                suburb = "Sydney",
                postcode = "2000",
                latitude = -33.8696,
                longitude = 151.2069
            ),
            owner = Owner(
                first_name = "John", last_name = "Doe", dob = "1985-05-10", avatar = Avatar(
                    small = Image(url = "https://example.com/avatar/small.jpg"),
                    medium = Image(url = "https://example.com/avatar/medium.jpg"),
                    large = Image(url = "https://example.com/avatar/large.jpg")
                )
            ),
            property_images = listOf(
                PropertyImage(
                    id = 1, attachment = Image(url = "https://example.com/image1.jpg")
                ), PropertyImage(
                    id = 2, attachment = Image(url = "https://example.com/image2.jpg")
                )
            ),
            agent = Agent(
                first_name = "Jane",
                last_name = "Smith",
                company_name = "Real Estate Co.",
                avatar = Avatar(
                    small = Image(url = "https://example.com/agent/small.jpg"),
                    medium = Image(url = "https://example.com/agent/medium.jpg"),
                    large = Image(url = "https://example.com/agent/large.jpg")
                )
            ),
            property_type = "House",
            sale_type = "Sale"
        )
    )
)