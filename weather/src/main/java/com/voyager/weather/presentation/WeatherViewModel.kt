package com.voyager.weather.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
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

    var state = mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state.value = when (val result = loadWeather()) {
                is Result.Error -> {
                    Log.e("error", "${result.error.printStackTrace()}")
                    state.value.copy(error = "Error")
                }

                is Result.Loading -> {
                    Log.e("loading", "loading")
                    state.value.copy(isLoading = true)
                }

                is Result.Success -> {
                    Log.e("success", "success")
                    state.value.copy(weatherInfo = result.data)
                }
            }
        }
    }
}
