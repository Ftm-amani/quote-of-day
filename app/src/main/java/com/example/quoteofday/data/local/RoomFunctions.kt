package com.example.quoteofday.data.local

import com.example.quoteofday.data.models.Quotes
import javax.inject.Inject

//repo

class RoomFunctions @Inject constructor(
    private val quoteDao: QuoteDao
    ) {
    
    suspend fun addQuote(quotes: Quotes) = quoteDao.insert(quotes)
    
    suspend fun removeQuote(quotes: Quotes) = quoteDao.delete(quotes)
    
    fun getAllQuotes() = quoteDao.getAllQuotes()
    
//    suspend fun getOneQuote() = quoteDao.getOneQuote()
}