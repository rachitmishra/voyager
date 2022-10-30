package com.voyager.weather.data.repository

import com.voyager.utils.Result
import com.voyager.weather.data.mappers.toWeatherInfo
import com.voyager.weather.data.remote.WeatherApi
import com.voyager.weather.domain.repository.WeatherRepository
import com.voyager.weather.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
) : WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Result<WeatherInfo> {
        return try {
            println("$lat $long")

            val response = api.getWeatherData(lat, long).toWeatherInfo()
            Result.Success(
                data = response
            )
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
