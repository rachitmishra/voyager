package com.voyager

import MovieApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.voyager.movies.di.MovieModuleFactory
import com.voyager.movies.ui.Action
import com.voyager.ui.PermissionDelegate
import com.voyager.ui.theme.VoyagerTheme

class MainActivity : ComponentActivity() {

    private val viewModel by lazy {
        MovieModuleFactory(
            (application as VoyagerApplication).getApiFactory(MovieModuleFactory.BASE_URL)
        ).viewModel()
    }

    private val permissionDelegate by lazy {
        PermissionDelegate(this)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        checkLocationPermission {
//            viewModel.loadWeatherInfo()
//        }


        viewModel.perform(Action.GetPopular)
        viewModel.perform(Action.GetNowPlaying)
        viewModel.perform(Action.GetTopRated)

        setContent {
            VoyagerTheme {
                MovieApp(viewModel)
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VoyagerTheme {}
}
