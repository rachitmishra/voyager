package com.voyager.weather.data.mappers

import com.voyager.weather.data.remote.WeatherDataDto
import com.voyager.weather.domain.weather.IndexedWeatherData
import com.voyager.weather.domain.weather.WeatherData
import com.voyager.weather.domain.weather.WeatherInfo
import com.voyager.weather.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return hourly.time.mapIndexed { index, time ->
        val temperature = hourly.temperature2M[index]
        val pressure = hourly.pressureMsl[index]
        val humidity = hourly.relativehumidity2M[index]
        val windSpeed = hourly.windspeed_10m[index]
        val weatherCode = hourly.weathercode[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { indexedWeatherData ->
            indexedWeatherData.data
        }
    }
}

fun WeatherDataDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return WeatherInfo(weatherDataMap, currentWeatherData)
}
