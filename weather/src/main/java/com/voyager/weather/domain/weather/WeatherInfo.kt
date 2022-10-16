package com.voyager.weather.domain.weather

data class WeatherInfo(
    val dataPerDay: Map<Int, List<WeatherData>> = mapOf(),
    val dataNow: WeatherData? = null
)
