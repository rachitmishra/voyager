package com.voyager.weather.presentation

data class WeatherState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val weatherInfo: com.voyager.weather.domain.weather.WeatherInfo? = null
)
