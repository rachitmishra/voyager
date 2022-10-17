package com.voyager.weather.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDataDto(
    val latitude: Double,
    val longitude: Double,

    @SerialName("generationtime_ms")
    val generationtimeMS: Double,

    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Long,

    val timezone: String,

    @SerialName("timezone_abbreviation")
    val timezoneAbbreviation: String,

    val elevation: Double,

    @SerialName("hourly_units")
    val hourlyUnits: HourlyUnits,

    val hourly: Hourly,

    @SerialName("daily_units")
    val dailyUnits: DailyUnits,

    val daily: Daily
)
