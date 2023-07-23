package com.example.propertyapp.data.remote.repository

import com.example.propertyapp.data.remote.PropertyApi
import com.example.propertyapp.data.remote.dto.PropertyDto
import com.example.propertyapp.domain.PropertyRepository
import javax.inject.Inject

class PropertyRepositoryImpl @Inject constructor(
    private val api: PropertyApi
) : PropertyRepository {
    // Consider offline first functionalities
    override suspend fun getProperties(): PropertyDto = api.getProperties()
}