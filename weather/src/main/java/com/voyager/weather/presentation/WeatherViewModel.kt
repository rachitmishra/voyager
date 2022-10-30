package com.voyager.weather.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voyager.di.VGViewModel
import com.voyager.utils.Result
import com.voyager.weather.domain.weather.WeatherInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val loadWeather: UseCase<Result<WeatherInfo>>
) : ViewModel(), VGViewModel {

    var state = mutableStateOf<WeatherState>(WeatherState.Loading)
        private set

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state.value = when (val result = loadWeather()) {
                is Result.Error -> {
                    WeatherState.Error("${result.error.printStackTrace()}")
                }

                is Result.Success -> {
                    Log.e("success", "success")
                    WeatherState.Loaded(result.data!!)
                }

                is Result.Loading -> WeatherState.Loading
            }
        }
    }
}
