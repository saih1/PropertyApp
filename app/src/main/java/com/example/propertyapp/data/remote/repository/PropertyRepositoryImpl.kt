package com.example.propertyapp.data.remote.repository

import com.example.propertyapp.data.remote.PropertyApi
import com.example.propertyapp.data.remote.dto.PropertyDto
import com.example.propertyapp.domain.PropertyRepository
import javax.inject.Inject

class PropertyRepositoryImpl @Inject constructor(
    private val api: PropertyApi
) : PropertyRepository {
    override suspend fun getProperties(): PropertyDto = api.getProperties()
}