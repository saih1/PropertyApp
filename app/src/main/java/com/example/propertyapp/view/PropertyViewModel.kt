package com.example.propertyapp.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.propertyapp.common.RequestState
import com.example.propertyapp.data.remote.dto.PropertyDto
import com.example.propertyapp.domain.PropertyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyViewModel @Inject constructor(
    private val repo: PropertyRepository
) : ViewModel() {

    private val _properties: MutableStateFlow<RequestState<PropertyDto>> =
        MutableStateFlow(RequestState.idle())
    val properties: StateFlow<RequestState<PropertyDto>> = _properties.asStateFlow()

    init {
        fetchProperties()
    }

    fun fetchProperties() {
        viewModelScope.launch {
            try {
                _properties.value = RequestState.loading()
                val result = repo.getProperties()
                if (result.data != null) {
                    _properties.value = RequestState.success(PropertyDto(result.data))
                } else {
                    _properties.value = RequestState.error(Throwable(message = "Empty List"))
                }
            } catch (e: Exception) {
                _properties.value = RequestState.error(e)
            }
        }
    }
}