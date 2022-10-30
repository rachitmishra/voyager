package com.voyager.stocks.data.repository

import com.voyager.stocks.data.mapper.toCompanyListing
import com.voyager.stocks.data.remote.StockApi
import com.voyager.stocks.domain.model.CompanyListing
import com.voyager.stocks.domain.repository.CompanyListingResult
import com.voyager.stocks.domain.repository.FetchFrom
import com.voyager.stocks.domain.repository.Query
import com.voyager.stocks.domain.repository.StockRepository
import com.voyager.stocks.domain.repository.isValid
import com.voyager.utils.EventDelegate
import com.voyager.utils.LogDelegate
import com.voyager.utils.Result
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    private val db: DatabaseDelegate,
    private val log: LogDelegate,
    private val event: EventDelegate,
    private val parser: CSVParser<CompanyListing>
) : StockRepository {

    override suspend fun getCompanyListings(fetchFrom: FetchFrom, query: Query): CompanyListingResult {
        return flow {
            emit(Result.Loading())
            val localListings = db.searchCompanyListing(query.query)
            emit(Result.Success(localListings.map {
                it.toCompanyListing()
            }))
            val isDbEmpty = localListings.isEmpty() && query.isValid()
            val loadFromCache = !isDbEmpty && fetchFrom.local()

            if (loadFromCache) {
                emit(Result.Loading())
                return@flow
            }

            val remoteListing = try {
                val response = api.getListings(query.query)
//                parser.parse(response.byteStream)
            } catch (e: IOException) {

            } catch (e: HttpException) {

            }

            remoteListing?.let {

            }
        }

    }
}

