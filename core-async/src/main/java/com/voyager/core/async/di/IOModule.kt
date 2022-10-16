package com.voyager.core.async.di

import com.voyager.core.async.DispatcherDelegate
import com.voyager.core.async.DispatcherDelegateImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class IOModule {

    @Binds
    @Singleton
    abstract fun bindDispatcherDelegate(dispatcherDelegateImpl: DispatcherDelegateImpl):
            DispatcherDelegate
}
