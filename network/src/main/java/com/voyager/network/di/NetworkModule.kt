package com.voyager.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val contentType = "application/json".toMediaType()
        val factory = Json.asConverterFactory(contentType)
        return Retrofit.Builder().addConverterFactory(factory).build()
    }


    companion object {

        private fun getOkHttpClient() = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        @OptIn(ExperimentalSerializationApi::class)
        @JvmStatic
        fun getRetrofit(url: String): Retrofit {
            return Retrofit.Builder().baseUrl(url)
                .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                .client(getOkHttpClient())
                .build()
        }
    }

}
