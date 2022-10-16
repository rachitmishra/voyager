package com.voyager.stocks.data.repository

import com.opencsv.CSVReader
import com.voyager.stocks.domain.model.CompanyListing
import com.voyager.core.async.DispatcherDelegate
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CompanyListingParser @Inject constructor(private val dispatcher: com.voyager.core.async.DispatcherDelegate) : CSVParser<CompanyListing> {
    override suspend fun parse(stream: InputStream): List<CompanyListing> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(dispatcher.io) {
            csvReader.readAll().drop(1).mapNotNull { line ->
                val symbol = line.getOrNull(0) ?: return@mapNotNull null
                val name = line.getOrNull(0) ?: return@mapNotNull null
                val exchange = line.getOrNull(0) ?: return@mapNotNull null
                CompanyListing(name, symbol, exchange)
            }.also {
                csvReader.close()
            }
        }
    }
}
