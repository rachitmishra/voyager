package com.voyager.weather.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Daily(
    val time: List<String>,
    val weathercode: List<Double>,

    @SerialName("temperature_2m_max")
    val temperature2MMax: List<Double>,

    @SerialName("temperature_2m_min")
    val temperature2MMin: List<Double>,

    val sunrise: List<String>,
    val sunset: List<String>,

    @SerialName("rain_sum")
    val rainSum: List<Double>,

    @SerialName("showers_sum")
    val showersSum: List<Double>,

    @SerialName("snowfall_sum")
    val snowfallSum: List<Double>
)
