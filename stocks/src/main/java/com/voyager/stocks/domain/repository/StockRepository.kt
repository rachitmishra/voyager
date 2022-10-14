package com.voyager.stocks.domain.repository

import com.voyager.stocks.domain.model.CompanyListing
import com.voyager.utils.Result
import kotlinx.coroutines.flow.Flow

@JvmInline
value class Query(val query: String)

sealed interface FetchFrom {
    object Remote : FetchFrom
    object Local : FetchFrom

    fun local() = this == Local
}

fun Query.isValid() = query.isNotBlank()

typealias CompanyListingResult = Flow<Result<List<CompanyListing>>>

interface StockRepository {

    suspend fun getCompanyListings(
        fetchFrom: FetchFrom,
        query: Query
    ): CompanyListingResult
}
