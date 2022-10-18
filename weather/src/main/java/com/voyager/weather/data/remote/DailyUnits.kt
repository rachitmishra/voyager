package com.voyager.weather.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyUnits(
    val time: String,
    val weathercode: String,

    @SerialName("temperature_2m_max")
    val temperature2MMax: String,

    @SerialName("temperature_2m_min")
    val temperature2MMin: String,

    val sunrise: String,
    val sunset: String,

    @SerialName("rain_sum")
    val rainSum: String,

    @SerialName("showers_sum")
    val showersSum: String,

    @SerialName("snowfall_sum")
    val snowfallSum: String
)
