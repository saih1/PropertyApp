package com.example.propertyapp.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.propertyapp.common.RequestState
import com.example.propertyapp.common.asEntity
import com.example.propertyapp.domain.PropertyRepository
import com.example.propertyapp.domain.model.PropertyEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PropertyViewModel @Inject constructor(
    private val repo: PropertyRepository
) : ViewModel() {

    private val _properties: MutableStateFlow<RequestState<List<PropertyEntity>>> =
        MutableStateFlow(RequestState.idle())
    val properties: StateFlow<RequestState<List<PropertyEntity>>> = _properties.asStateFlow()

    init { fetchProperties() }

    // TODO: Should consider using a use_case to improve testability
    fun fetchProperties() {
        viewModelScope.launch {
            try {
                _properties.value = RequestState.loading()
                delay(2000L)
                val propertyList: List<PropertyEntity> = repo.getProperties().asEntity()
                _properties.value = RequestState.success(propertyList)
            } catch (e: HttpException) {
                _properties.value = RequestState.error(e)
            } catch (e: IOException) {
                _properties.value = RequestState.error(e)
            } catch (e: Exception) {
                _properties.value = RequestState.error(e)
            }
        }
    }
}