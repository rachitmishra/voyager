package com.voyager.weather.data.remote

data class Daily (
    val time: List<String>,
    val weathercode: List<Double>,

    @Json(name = "temperature_2m_max")
    val temperature2MMax: List<Double>,

    @Json(name = "temperature_2m_min")
    val temperature2MMin: List<Double>,

    val sunrise: List<String>,
    val sunset: List<String>,

    @Json(name = "rain_sum")
    val rainSum: List<Double>,

    @Json(name = "showers_sum")
    val showersSum: List<Double>,

    @Json(name = "snowfall_sum")
    val snowfallSum: List<Double>
)
