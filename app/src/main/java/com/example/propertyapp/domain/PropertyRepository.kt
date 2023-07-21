package com.example.propertyapp.domain

import com.example.propertyapp.data.remote.dto.PropertyDto

interface PropertyRepository {
    suspend fun getProperties(): PropertyDto
}