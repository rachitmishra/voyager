package com.voyager.movies.ui.state

import java.util.UUID

sealed class PageError(val msg: String = "") {
    val id: String = UUID.randomUUID().toString()

    object ConnectionError : PageError(msg = "Connection Error")
    data class ApiError(val message: String) : PageError(msg = message)
    data class SearchError(val query: String) : PageError(msg = "$query Error")

}
