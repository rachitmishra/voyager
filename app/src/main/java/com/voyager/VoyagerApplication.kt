package com.voyager

import android.app.Application
import com.voager.location.LocationDelegateImpl
import com.voyager.core.di.DependencyManager
import com.voyager.core.di.DispatcherFactory
import com.voyager.core.di.LocationFactory
import com.voyager.core.di.PermissionFactory
import com.voyager.core.di.RepositoryFactory
import com.voyager.permissions.PermissionManagerImpl
import com.voyager.weather.data.repository.WeatherRepositoryImpl
import dagger.hilt.android.HiltAndroidApp




@HiltAndroidApp
class VoyagerApplication : Application() {

    fun appFactory(): DependencyManager {
        return object: DependencyManager {

            override fun locationFactory(): LocationFactory {
                return object: LocationFactory {
                    override fun <T> get(): T {
                        LocationDelegateImpl()
                    }
                }
            }

            override fun permissionFactory(): PermissionFactory {
                return object: PermissionFactory {
                    override fun <T> get(): T {
                        return PermissionManagerImpl(this@VoyagerApplication) as T
                    }
                }
            }

            override fun repositoryFactory(): RepositoryFactory {
                return object: RepositoryFactory {
                    override fun <T> get(): T {
                        return WeatherRepositoryImpl() as T
                    }
                }
            }

            override fun dispatcherFactory(): DispatcherFactory {
                TODO("Not yet implemented")
            }
        }
    }
}
