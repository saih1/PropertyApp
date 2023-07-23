package com.example.propertyapp.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.propertyapp.common.RequestState
import com.example.propertyapp.domain.model.PropertyEntity
import com.example.propertyapp.domain.use_case.GetPropertiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PropertyViewModel @Inject constructor(
    private val getPropertiesUseCase: GetPropertiesUseCase
) : ViewModel() {

    private val _properties: MutableStateFlow<RequestState<List<PropertyEntity>>> =
        MutableStateFlow(RequestState.idle())
    val properties: StateFlow<RequestState<List<PropertyEntity>>> = _properties.asStateFlow()

    private val _selectedProperty: MutableStateFlow<PropertyEntity?> = MutableStateFlow(null)
    val selectedProperty: StateFlow<PropertyEntity?> = _selectedProperty.asStateFlow()

    init { fetchProperties() }

    fun fetchProperties() {
        getPropertiesUseCase.doAction()
            .onEach { _properties.value = it }
            .launchIn(viewModelScope)
    }

    fun selectProperty(property: PropertyEntity) {
        _selectedProperty.value = property
    }

    fun clearPropertySelection() {
        _selectedProperty.value = null
    }
}