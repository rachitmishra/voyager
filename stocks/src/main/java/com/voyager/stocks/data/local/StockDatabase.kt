package com.voyager.stocks.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.voyager.stocks.data.repository.DatabaseDelegate
import com.voyager.stocks.domain.repository.Query

@Database(
    entities = [CompanyListingEntity::class], version = 1
)
abstract class StockDatabase : RoomDatabase(), DatabaseDelegate {

    abstract val dao: StockDao

    override suspend fun getCompanyListing(id: String): CompanyListingEntity {
        return dao.getCompanyListing(id)
    }

    override suspend fun searchCompanyListing(query: String): List<CompanyListingEntity> {
        return dao.searchCompanyListing(query)
    }
}
