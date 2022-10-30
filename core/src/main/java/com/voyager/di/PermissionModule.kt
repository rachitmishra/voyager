package com.voyager.di

import com.voyager.permissions.PermissionDelegate
import com.voyager.permissions.PermissionDelegateImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PermissionModule {

    @Binds
    @Singleton
    abstract fun bindPermissionManager(permissionManagerImpl: PermissionDelegateImpl): PermissionDelegate
}
