package com.voyager.weather.presentation

import com.voyager.weather.domain.weather.WeatherInfo

sealed class WeatherState {
    object Loading : WeatherState()
    data class Error(val msg: String) : WeatherState()
    data class Loaded(val weatherInfo: WeatherInfo) : WeatherState()
}
