package com.voyager.permissions.di

import com.voyager.permissions.PermissionManager
import com.voyager.permissions.PermissionManagerImpl
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
    abstract fun bindPermissionManager(permissionManagerImpl: PermissionManagerImpl): PermissionManager
}
