package com.voyager.weather.data.mappers

import com.voyager.weather.data.remote.WeatherDataDto
import com.voyager.weather.domain.WeatherData
import com.voyager.weather.domain.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return hourly.time.mapIndexed { index, time ->
        val temperature = hourly.temperature2M[index]
        val pressure = hourly.pressureMsl[index]
        val humidity = hourly.relativehumidity2M[index]
        val windSpeed = hourly.windspeed_10m[index]
        val weatherCode = hourly.weathercode[index]
        WeatherData(
            time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME), temperatureCelsius = temperature,
            pressure = pressure,
            windSpeed = windSpeed,
            humidity = humidity,
            weatherType = WeatherType.fromWMO(weatherCode)
        )
    }.groupBy {
        it.time.dayOfMonth
    }
}
