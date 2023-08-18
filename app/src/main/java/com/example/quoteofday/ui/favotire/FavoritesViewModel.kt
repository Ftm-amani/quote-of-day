package com.example.quoteofday.ui.favotire

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quoteofday.data.local.RoomFunctions
import com.example.quoteofday.data.models.Quotes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val roomFunctions: RoomFunctions
) : ViewModel() {
    fun toggleQuoteFavorite(quote: Quotes) {
        viewModelScope.launch {
            val updatedQuote = quote.copy(isFave = !quote.isFave!!)
            roomFunctions.addQuote(updatedQuote)
        }
    }
    
    val favoriteQuotes: Flow<List<Quotes>> = roomFunctions.getFavoriteQuotes()
}