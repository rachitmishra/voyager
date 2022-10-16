package com.voyager.core.async

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherDelegate {
    val io: CoroutineDispatcher
}
