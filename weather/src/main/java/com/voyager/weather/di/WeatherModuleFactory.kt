package com.voyager.weather.di

import com.voager.location.LocationDelegate
import com.voyager.di.Dep
import com.voyager.di.VGDependencyFactory
import com.voyager.di.VGModuleFactory
import com.voyager.di.VGRemoteSourceFactory
import com.voyager.di.VGViewModel
import com.voyager.weather.data.remote.WeatherApi
import com.voyager.weather.data.repository.WeatherRepositoryImpl
import com.voyager.weather.domain.repository.WeatherRepository
import com.voyager.weather.presentation.LoadWeatherInfoUseCase
import com.voyager.weather.presentation.WeatherViewModel

class WeatherModuleFactory(
    private val depFactory: VGDependencyFactory,
    private val remoteSourceFactory: VGRemoteSourceFactory
) : VGModuleFactory {

    companion object {
        const val baseUrl = "https://api.open-meteo.com/v1/"
    }

    private fun getLoadWeatherInfoUseCase(): LoadWeatherInfoUseCase {

        val locationDelegate = depFactory.getDep(Dep.Location) as LocationDelegate
        return LoadWeatherInfoUseCase(createRepository(), locationDelegate)
    }

    override fun createVM(): VGViewModel {
        return WeatherViewModel(getLoadWeatherInfoUseCase())
    }

    private fun createRepository(): WeatherRepository {
        return WeatherRepositoryImpl(remoteSourceFactory.createSource(WeatherApi::class.java))
    }
}
