package com.voyager.core.async

import kotlinx.coroutines.Dispatchers

class DispatcherDelegateImpl : DispatcherDelegate {
    override val io = Dispatchers.IO
}
