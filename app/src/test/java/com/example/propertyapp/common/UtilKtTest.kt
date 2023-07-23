package com.example.propertyapp.common

import com.example.propertyapp.domain.model.PropertyEntity
import com.example.propertyapp.test_util.fakePropertyDto
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UtilKtTest {

    @Test
    fun `toReadableDate() returns readable date string if input is valid`() {
        // Arrange
        val inputDate = "2020-11-23T20:00:00+10:00"
        val expected = "23/11/2020"

        // Act + Assert
        assertThat(inputDate.toReadableDate()).isEqualTo(expected)
    }

    @Test
    fun `toReadableDate() returns null if input is invalid`() {
        // Arrange
        val inputDate = "invalidDateFormat"
        val expected = null

        // Act + Assert
        assertThat(inputDate.toReadableDate()).isEqualTo(expected)
    }

    @Test
    fun `test asEntity()`() {
        // Arrange
        val expectedResult = PropertyEntity(
            id = 12345,
            agentName = "Jane Smith",
            agentAvatar = "https://example.com/agent/medium.jpg",
            agentCompany = "Real Estate Co.",
            propertyAddress = "123 Main Street",
            bedroomCount = 3,
            bathroomCount = 2,
            carspaceCount = 1,
            propertyImage = "https://example.com/image1.jpg",
            propertyPrice = "$500,000",
            propertyType = "House",
            auctionDate = "15/08/2023",
            description = "This is a beautiful property."
        )

        // Act
        val actualResult: PropertyEntity = fakePropertyDto.asEntity().first()

        // Assert
        assertThat(actualResult).isEqualTo(expectedResult)
    }
}