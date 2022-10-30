package com.voyager.weather.presentation

import com.voager.location.LocationDelegate
import com.voyager.utils.Result
import com.voyager.weather.domain.repository.WeatherRepository
import com.voyager.weather.domain.weather.WeatherInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoadWeatherInfoUseCase @Inject constructor(
    private val repository: WeatherRepository,
    private val locationDelegate: LocationDelegate,
) : UseCase<Result<WeatherInfo>> {

    override suspend operator fun invoke(): Result<WeatherInfo> {

        val location = locationDelegate.getLocation()
        return withContext(Dispatchers.IO) {
            repository.getWeatherData(location.lat, location.lang)
        }
    }
}
