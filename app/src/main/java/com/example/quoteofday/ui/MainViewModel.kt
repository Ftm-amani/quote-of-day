package com.example.quoteofday.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    
    suspend fun insert() = roomFunctions.addQuote(ListOfQuotes.allQuotes[0])
    
    //is a live data
    fun getAllRepos() = roomFunctions.getAllQuotes()

    fun removeFaveRepository(quotes: Quotes) = viewModelScope.launch {
        roomFunctions.removeQuote(quotes)
    }
}