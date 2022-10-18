package com.voyager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.voyager.core.di.VGViewModelFactory
import com.voyager.ui.theme.VoyagerTheme
import com.voyager.weather.presentation.WeatherApp
import com.voyager.weather.presentation.WeatherViewModel

class MainActivity : ComponentActivity() {

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        getViewModel("weather") as WeatherViewModel
    }

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkLocationPermission {
            viewModel.loadWeatherInfo()
        }

        setContent {
            VoyagerTheme {
                WeatherApp(viewModel)
            }
        }
    }

    private fun checkLocationPermission(onResult: () -> Unit) {
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            onResult()
        }
        permissionLauncher.launch(
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }
}

fun MainActivity.getViewModel(module: String) =
    (application as VGViewModelFactory).getOrCreate(module)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VoyagerTheme {}
}
