package com.voyager.stocks.data.repository

import com.voyager.stocks.domain.model.CompanyListing
import java.io.InputStream

interface CSVParser<T> {

    suspend fun parse(stream: InputStream): List<CompanyListing>
}
