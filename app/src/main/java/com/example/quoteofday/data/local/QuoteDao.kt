package com.example.quoteofday.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.quoteofday.data.models.Quotes
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {
    
    @Query("SELECT * from quotes_table")
    fun getAllQuotes(): Flow<List<Quotes>>
    
    @Query("SELECT * from quotes_table")
    fun getOneQuote(): Flow<Quotes>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(quotes: Quotes)
    
    @Delete
    suspend fun delete(quotes: Quotes)
    
    @Query("SELECT * FROM quotes_table WHERE isFave = 1")
    fun getFavoriteQuotes(): Flow<List<Quotes>>
}