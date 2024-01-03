package com.example.quoteofday.data.local

import androidx.room.*
import com.example.quoteofday.data.models.QuoteNameAndSelection
import com.example.quoteofday.data.models.Quotes
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {
    
    @Query("SELECT * from quotes_table")
    fun getAllQuotes(): Flow<List<Quotes>>

    @Query("SELECT DISTINCT quote_name, is_selected FROM quotes_table")
    fun getDistinctQuoteNamesAndSelection(): Flow<List<QuoteNameAndSelection>>

    @Query("SELECT * from quotes_table")
    fun getOneQuote(): Flow<Quotes>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(quotes: Quotes)
    
    @Delete
    suspend fun delete(quotes: Quotes)
    
    @Query("SELECT * FROM quotes_table WHERE is_fave = 1")
    fun getFavoriteQuotes(): Flow<List<Quotes>>

    @Update
    suspend fun updateQuotes(quotes: Quotes)

    @Query("UPDATE quotes_table SET is_selected =:isSelected WHERE quote_name==:quoteName ")
    suspend fun updateQuotesByType(quoteName:String, isSelected: Boolean)

    @Query("SELECT * FROM quotes_table WHERE is_selected = 1")
    fun getSelectedQuotes(): Flow<List<Quotes>>

}