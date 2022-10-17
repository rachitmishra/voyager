package com.voyager.core.di

interface VGDependencyFactory {
    fun getDep(key: Dep): VGDependency
}
sealed class Dep {
    object Location: Dep()
    object Permission: Dep()
    object Dispatcher: Dep()
}

interface VGViewModelFactory {
    fun getOrCreate(key: String): VGViewModel
}

interface VGRemoteSourceFactory {
    fun <T> createSource(source: Class<T>): T
}

interface VGModuleFactory {
    fun createVM(): VGViewModel
}

interface VGViewModel

interface VGDependency

interface VGRepository
