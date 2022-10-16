package com.voyager.weather.presentation

data class WeatherState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val weatherInfo: com.voyager.weather.domain.weather.WeatherInfo? = null
)
