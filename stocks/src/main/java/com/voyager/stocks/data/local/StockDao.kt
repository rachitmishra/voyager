package com.voyager.stocks.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyListings(
        listings: List<CompanyListingEntity>
    )

    @Query("DELETE FROM companylistingentity")
    suspend fun clearCompanyListings(
        listings: List<CompanyListingEntity>
    )

    @Query(
        """
        SELECT *
        FROM companylistingentity
        WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR 
        UPPER(:query) == symbol
    """
    )
    suspend fun searchCompanyListing(query: String): List<CompanyListingEntity>

    @Query("SELECT * FROM companylistingentity WHERE id=:id")
    suspend fun getCompanyListing(id: String): CompanyListingEntity
}
