package com.voyager.weather.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast?latitude=28.6353&longitude=77.2250&hourly=temperature_2m,relativehumidity_2m,dewpoint_2m,precipitation,rain,showers,snowfall,freezinglevel_height,weathercode,pressure_msl&daily=weathercode,temperature_2m_max,temperature_2m_min,sunrise,sunset,rain_sum,showers_sum,snowfall_sum&timezone=auto")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double,
    ): WeatherDataDto

    companion object {
        const val URL = "https://api.open-meteo.com/v1/"
    }
}
