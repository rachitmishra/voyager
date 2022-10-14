package com.voyager.stocks.data.repository

import com.voyager.stocks.data.local.CompanyListingEntity

interface DatabaseDelegate {

    suspend fun getCompanyListing(id: String): CompanyListingEntity
    suspend fun searchCompanyListing(query: String): List<CompanyListingEntity>
}
