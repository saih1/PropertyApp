package com.example.propertyapp.domain.use_case

import com.example.propertyapp.common.RequestState
import com.example.propertyapp.common.Status
import com.example.propertyapp.common.asEntity
import com.example.propertyapp.data.remote.dto.PropertyDto
import com.example.propertyapp.domain.PropertyRepository
import com.example.propertyapp.test_util.fakePropertyDto
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import com.google.common.truth.Truth.assertThat
import retrofit2.Response
import java.io.IOException

class MockPropertyRepository : PropertyRepository {
    private var httpException: Boolean = false
    private var ioException: Boolean = false
    private var data = PropertyDto(emptyList())

    override suspend fun getProperties(): PropertyDto {
        if (httpException) throw HttpException(
            Response.error<Any>(
                /* code = */ 500,
                /* body = */ "Http Exception".toResponseBody("text/plain".toMediaTypeOrNull())
            )
        )
        if (ioException) throw IOException("IO Exception")
        return data
    }

    fun populateData(input: PropertyDto) {
        data = input
    }

    fun shouldThrowHTTPException(boolean: Boolean) {
        httpException = boolean
    }

    fun shouldThrowIOException(boolean: Boolean) {
        ioException = boolean
    }

    fun reset() {
        httpException = false
        ioException = false
        data = PropertyDto(emptyList())
    }
}

class GetPropertiesUseCaseTest {
    private lateinit var mockRepo: MockPropertyRepository
    private lateinit var getPropertyUseCase: GetPropertiesUseCase
    private val testDto = fakePropertyDto

    @Before
    fun setup() {
        mockRepo = MockPropertyRepository()
        getPropertyUseCase = GetPropertiesUseCase(mockRepo)
    }

    @After
    fun teardown() {
        mockRepo.reset()
    }

    @Test
    fun `Successful request returns list of properties`() {
        runBlocking {
            // Arrange
            mockRepo.populateData(testDto)
            val expectedResult = RequestState.success(fakePropertyDto.asEntity())

            // Act
            val actualResult = getPropertyUseCase.doAction().last()

            // Assert
            assertThat(actualResult).isEqualTo(expectedResult)
        }
    }

    @Test
    fun `Handles HTTP Exception`() {
        runBlocking {
            // Arrange
            mockRepo.shouldThrowHTTPException(true)

            // Act
            val actualResult = getPropertyUseCase.doAction().last()

            // Assert
            assertThat(actualResult.status).isEqualTo(Status.ERROR)
            assertThat(actualResult.throwable).isInstanceOf(HttpException::class.java)
        }
    }

    @Test
    fun `Handles IO Exception`() {
        runBlocking {
            // Arrange
            mockRepo.shouldThrowIOException(true)

            // Act
            val actualResult = getPropertyUseCase.doAction().last()

            // Assert
            assertThat(actualResult.status).isEqualTo(Status.ERROR)
            assertThat(actualResult.throwable).isInstanceOf(IOException::class.java)
        }
    }
}