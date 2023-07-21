package com.example.propertyapp.di

import com.example.propertyapp.data.remote.BASE_URL
import com.example.propertyapp.data.remote.PropertyApi
import com.example.propertyapp.data.remote.repository.PropertyRepositoryImpl
import com.example.propertyapp.domain.PropertyRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providePropertyApi(): PropertyApi {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(PropertyApi::class.java)
    }

    @Provides
    @Singleton
    fun providePropertyRepository(api: PropertyApi): PropertyRepository =
        PropertyRepositoryImpl(api)
}