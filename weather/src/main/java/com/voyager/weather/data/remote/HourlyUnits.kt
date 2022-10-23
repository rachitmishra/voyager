package com.voyager.weather.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyUnits(
    val time: String,

    @SerialName("temperature_2m")
    val temperature2M: String,

    @SerialName("relativehumidity_2m")
    val relativehumidity2M: String,

    @SerialName("windspeed_10m")
    val windspeed10m: String,

    @SerialName("dewpoint_2m")
    val dewpoint2M: String,

    val precipitation: String,
    val rain: String,
    val showers: String,
    val snowfall: String,

    @SerialName("freezinglevel_height")
    val freezinglevelHeight: String,

    val weathercode: String,

    @SerialName("pressure_msl")
    val pressureMsl: String
)
