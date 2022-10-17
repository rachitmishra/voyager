package com.voyager.weather.presentation

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voyager.core.di.VGViewModel
import com.voyager.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val loadWeather: LoadWeatherInfoUseCase
) : ViewModel(), VGViewModel {

    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = when (val result = loadWeather()) {
                is Result.Error -> {
                    Log.e("error", "${result.error.printStackTrace()}")
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
