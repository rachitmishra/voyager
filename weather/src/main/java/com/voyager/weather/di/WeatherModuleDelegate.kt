package com.voyager.weather.di

import androidx.lifecycle.ViewModel
import com.voyager.core.di.DependencyManager
import com.voyager.core.di.UseCaseFactory
import com.voyager.weather.presentation.WeatherViewModel

object WeatherModuleDelegate {

    @JvmStatic
    fun getViewModelFor(key: String, dependencyManager: DependencyManager, usecaseFactory: UseCaseFactory): ViewModel {
        if (key == "WeatherViewModel") {
            return WeatherViewModel(usecaseFactory)
        }
    }
}
