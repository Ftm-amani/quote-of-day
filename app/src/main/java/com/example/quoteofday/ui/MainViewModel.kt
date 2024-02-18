package com.example.quoteofday.ui

import androidx.lifecycle.ViewModel
import com.example.quoteofday.data.local.ListOfQuotes
import com.example.quoteofday.data.local.RoomFunctions
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun getSelectedQuotes() = roomFunctions.getSelectedQuotes()
}