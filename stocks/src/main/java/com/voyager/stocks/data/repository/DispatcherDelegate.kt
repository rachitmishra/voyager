package com.voyager.stocks.data.repository

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherDelegate {
    val io: CoroutineDispatcher
}
