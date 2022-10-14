package com.voyager.stocks.data.mapper

import com.voyager.stocks.data.local.CompanyListingEntity
import com.voyager.stocks.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name, symbol = symbol, exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name, symbol = symbol, exchange = exchange
    )
}
