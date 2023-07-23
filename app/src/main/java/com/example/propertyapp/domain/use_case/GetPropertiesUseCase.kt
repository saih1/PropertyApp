package com.example.propertyapp.domain.use_case

import com.example.propertyapp.common.RequestState
import com.example.propertyapp.common.asEntity
import com.example.propertyapp.domain.PropertyRepository
import com.example.propertyapp.domain.model.PropertyEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPropertiesUseCase @Inject constructor(
    private val repository: PropertyRepository
) {
    fun doAction(): Flow<RequestState<List<PropertyEntity>>> = flow {
        try {
            emit(RequestState.loading())
            delay(2000)
            val properties: List<PropertyEntity> =
                repository.getProperties().asEntity()
            emit(RequestState.success(properties))
        } catch (e: HttpException) {
            emit(RequestState.error(e))
        } catch (e: IOException) {
            emit(RequestState.error(e))
        } catch (e: Exception) {
            emit(RequestState.error(e))
        }
    }
}