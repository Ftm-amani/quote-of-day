package com.example.quoteofday.ui.addquote

import com.example.quoteofday.data.models.Quotes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quoteofday.data.local.RoomFunctions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddQuoteViewModel @Inject constructor(
    private val roomFunctions: RoomFunctions
) : ViewModel() {

    // Function to add a new quote
    fun addQuote(quoteText: String, quoteName: String, isFave: Boolean, isSelected: Boolean) {

        val newQuote = Quotes(
            quoteText = quoteText,
            quoteName = quoteName,
            isFave = isFave,
            isSelected = isSelected
        )

        viewModelScope.launch {
            roomFunctions.addQuote(newQuote)
        }
    }
}