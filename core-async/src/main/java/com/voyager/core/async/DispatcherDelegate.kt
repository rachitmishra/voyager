package com.voyager.core.async

import com.voyager.core.di.VGDependency
import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherDelegate: VGDependency {
    val io: CoroutineDispatcher
}
