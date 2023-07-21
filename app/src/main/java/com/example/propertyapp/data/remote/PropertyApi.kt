package com.example.propertyapp.data.remote

import com.example.propertyapp.data.remote.dto.PropertyDto
import retrofit2.http.GET

const val BASE_URL = "https://f213b61d-6411-4018-a178-53863ed9f8ec.mock.pstmn.io/"

interface PropertyApi {
    @GET("properties")
    suspend fun getProperties(): PropertyDto
}