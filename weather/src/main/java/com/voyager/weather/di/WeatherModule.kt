package com.voyager.weather.di

import com.voyager.weather.data.remote.WeatherApi
import com.voyager.weather.data.repository.WeatherRepositoryImpl
import com.voyager.weather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherModule {

   companion object {
       @Provides
       @Singleton
       fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
           return retrofit.create(WeatherApi::class.java)
       }
   }

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl):
            WeatherRepository
}
