package com.example.quoteofday.ui.setting.category

import androidx.lifecycle.ViewModel
import com.example.quoteofday.data.local.RoomFunctions
import com.example.quoteofday.data.models.QuoteNameAndSelection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ChooseCategoriesViewModel @Inject constructor(
    private val roomFunctions: RoomFunctions
) : ViewModel() {

    val distinctQuoteNamesAndSelection: Flow<List<QuoteNameAndSelection>> =
        roomFunctions.getDistinctQuoteNamesAndSelection()

    suspend fun updateQuotesByType(selectedCategory: MutableList<QuoteNameAndSelection>) {
        selectedCategory.forEach {
            roomFunctions.updateQuotesByType(it.quote_name,it.is_selected)
        }
    }
}