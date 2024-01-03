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

    fun getFavoriteQuotes() = quoteDao.getFavoriteQuotes()

    suspend fun updateQuotes(quotes: Quotes) = quoteDao.updateQuotes(quotes)

    suspend fun updateQuotesByType(quoteName:String, isSelected: Boolean)=quoteDao.updateQuotesByType(quoteName,isSelected)

    fun getSelectedQuotes() = quoteDao.getSelectedQuotes()

    fun getDistinctQuoteNamesAndSelection() = quoteDao.getDistinctQuoteNamesAndSelection()

}