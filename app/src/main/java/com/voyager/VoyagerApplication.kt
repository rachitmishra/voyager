package com.voyager

import android.app.Application
import com.voyager.di.ApiFactory
import com.voyager.network.NetworkModule
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class VoyagerApplication : Application() {

    fun getApiFactory(url: String): ApiFactory = object : ApiFactory {
        override fun <T> create(service: Class<T>?): T {
            return NetworkModule.getRetrofit(this@VoyagerApplication, url).create(service)
        }
    }
}
