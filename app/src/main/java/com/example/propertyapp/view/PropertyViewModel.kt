package com.example.propertyapp.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.propertyapp.data.remote.dto.PropertyDto
import com.example.propertyapp.domain.PropertyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyViewModel @Inject constructor(
    private val repo: PropertyRepository
) : ViewModel() {
    val properties: MutableStateFlow<PropertyDto> = MutableStateFlow(PropertyDto(emptyList()))

    init {
        viewModelScope.launch {
            val result = repo.getProperties()
            properties.value = result
        }
    }
}