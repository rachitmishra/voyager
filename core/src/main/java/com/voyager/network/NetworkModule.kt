package com.voyager.network

import android.app.Application
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.io.File
import java.io.IOException
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

    class ApiKeyInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val url: HttpUrl = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("api_key", "4a3f2df54ca37b88521628ba6ce35ae5")
                .build()
            val request: Request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            return chain.proceed(request)
        }
    }

    companion object {

        private fun getOkHttpClient(application: Application) = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .cache(
                Cache(
                    directory = File(application.cacheDir, "http_cache"),
                    // $0.05 worth of phone storage in 2020
                    maxSize = 50L * 1024L * 1024L // 50 MiB
                )
            )
            .build()

        @OptIn(ExperimentalSerializationApi::class)
        @JvmStatic
        fun getRetrofit(application: Application, url: String): Retrofit {
            return Retrofit.Builder().baseUrl(url)
                .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                .client(getOkHttpClient(application))
                .build()
        }
    }

}
