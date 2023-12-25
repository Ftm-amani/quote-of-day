package com.example.quoteofday.data.local

import androidx.room.*
import com.example.quoteofday.data.models.Quotes
import com.example.quoteofday.data.models.QuotesType
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {
    
    @Query("SELECT * from quotes_table")
    fun getAllQuotes(): Flow<List<Quotes>>

    @Query(
        "SELECT quotes_table.* FROM quotes_table " +
                "INNER JOIN quotes_types " +
                "ON quotes_table.id = quotes_types.id" +
                " WHERE quotes_types.isSelected = 1"
    )
    fun getSelectedQuotes(): Flow<List<Quotes>>
    
    @Query("SELECT * from quotes_table")
    fun getOneQuote(): Flow<Quotes>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(quotes: Quotes)
    
    @Delete
    suspend fun delete(quotes: Quotes)
    
    @Query("SELECT * FROM quotes_table WHERE isFave = 1")
    fun getFavoriteQuotes(): Flow<List<Quotes>>

    @Update
    suspend fun updateQuotes(quotes: Quotes)

    @Update
    suspend fun updateQuotesType(quotesType: QuotesType)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotesType(quotesType: QuotesType)

}