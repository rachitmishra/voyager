package com.voyager

import android.app.Application
import android.content.Intent
import com.voager.location.LocationDelegateImpl
import com.voyager.core.async.DispatcherDelegateImpl
import com.voyager.core.di.Dep
import com.voyager.core.di.VGDependency
import com.voyager.core.di.VGDependencyFactory
import com.voyager.core.di.VGModuleFactory
import com.voyager.core.di.VGRemoteSourceFactory
import com.voyager.core.di.VGViewModel
import com.voyager.core.di.VGViewModelFactory
import com.voyager.network.di.NetworkModule
import com.voyager.permissions.PermissionDelegateImpl
import com.voyager.weather.di.WeatherModuleFactory
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class VoyagerApplication : Application(), VGViewModelFactory, VGDependencyFactory {

    private val modules = hashMapOf("weather" to moduleFor("weather"))

    private fun moduleFor(name: String): VGModuleFactory {
        return when (name) {
            else -> WeatherModuleFactory(this, object : VGRemoteSourceFactory {
                private val retrofit = NetworkModule.getRetrofit(WeatherModuleFactory.baseUrl)

                override fun <T> createSource(source: Class<T>): T {
                    return retrofit.create(source)
                }
            })
        }
    }


    override fun getDep(key: Dep): VGDependency {
        return when (key) {
            Dep.Dispatcher -> DispatcherDelegateImpl()
            Dep.Location -> LocationDelegateImpl(this, PermissionDelegateImpl(this))
            Dep.Permission -> PermissionDelegateImpl(this)
        }
    }

    override fun getOrCreate(key: String): VGViewModel {
        return modules.getOrDefault(key, moduleFor(key)).createVM()
    }
}
