package com.voyager.weather.data.remote

data class DailyUnits (
    val time: String,
    val weathercode: String,

    @Json(name = "temperature_2m_max")
    val temperature2MMax: String,

    @Json(name = "temperature_2m_min")
    val temperature2MMin: String,

    val sunrise: String,
    val sunset: String,

    @Json(name = "rain_sum")
    val rainSum: String,

    @Json(name = "showers_sum")
    val showersSum: String,

    @Json(name = "snowfall_sum")
    val snowfallSum: String
)
