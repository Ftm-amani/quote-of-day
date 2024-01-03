package com.example.quoteofday.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quoteofday.TAG
import com.example.quoteofday.data.QuotesTypeName
import com.example.quoteofday.data.local.ListOfQuotes
import com.example.quoteofday.data.local.RoomFunctions
import com.example.quoteofday.data.models.Quotes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val roomFunctions: RoomFunctions
    ) : ViewModel() {

    suspend fun insert() {
        ListOfQuotes.allQuotes.forEach {
            roomFunctions.addQuote(it)
        }
    }

    fun getAllRepos() = roomFunctions.getAllQuotes()

    fun getSelectedQuotes() = roomFunctions.getSelectedQuotes()

    fun removeFaveRepository(quotes: Quotes) = viewModelScope.launch {
        roomFunctions.removeQuote(quotes)
    }
}