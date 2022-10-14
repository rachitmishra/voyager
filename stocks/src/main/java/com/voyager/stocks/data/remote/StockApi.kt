package com.voyager.stocks.data.remote

import retrofit2.http.Query

interface StockApi {

    suspend fun getListings(
        @Query("query") query: String
    )
}
