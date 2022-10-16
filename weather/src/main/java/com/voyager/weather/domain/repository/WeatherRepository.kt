package com.voyager.weather.domain.repository

import com.voyager.utils.Result
import com.voyager.weather.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Result<WeatherInfo>
}
