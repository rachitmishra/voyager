package com.voyager.weather.presentation

import com.voager.location.LocationDelegate
import com.voyager.core.async.DispatcherDelegate
import com.voyager.utils.Result
import com.voyager.weather.domain.repository.WeatherRepository
import com.voyager.weather.domain.weather.WeatherInfo
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface UseCase<T> {

    suspend fun invoke(): T
}

class LoadWeatherInfoUseCase @Inject constructor(
    private val repository: WeatherRepository,
    private val locationDelegate: LocationDelegate,
    private val dispatcherDelegate: DispatcherDelegate
) : UseCase<Result<WeatherInfo>> {

    override suspend operator fun invoke(): Result<WeatherInfo> {

        val location = locationDelegate.getLocation()
        return withContext(dispatcherDelegate.io) {
            repository.getWeatherData(location.lat, location.lang)
        }
    }
}
