package com.voyager.weather.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voyager.utils.Result
import com.voyager.weather.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val loadWeather: LoadWeatherInfoUseCase
) : ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = when (val result = loadWeather()) {
                is Result.Error -> {
                    state.copy(error = "Error")
                }

                is Result.Loading -> {
                    state.copy(isLoading = true)
                }

                is Result.Success -> {
                    state.copy(weatherInfo = result.data)
                }
            }
        }
    }
}
