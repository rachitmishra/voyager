package com.voyager.weather.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hourly(
    val time: List<String>,

    @SerialName("temperature_2m")
    val temperature2M: List<Double>,

    @SerialName("relativehumidity_2m")
    val relativehumidity2M: List<Double>,

    @SerialName("dewpoint_2m")
    val dewpoint2M: List<Double>,

    @SerialName("windspeed_10m")
    val windspeed_10m: List<Double>,

    val precipitation: List<Double>,
    val rain: List<Double>,
    val showers: List<Double>,
    val snowfall: List<Double>,

    @SerialName("freezinglevel_height")
    val freezinglevelHeight: List<Double>,

    val weathercode: List<Int>,

    @SerialName("pressure_msl")
    val pressureMsl: List<Double>
)
