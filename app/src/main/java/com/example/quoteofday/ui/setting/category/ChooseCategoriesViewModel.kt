package com.example.quoteofday.ui.setting.category

import androidx.lifecycle.ViewModel
import com.example.quoteofday.data.local.ListOfQuotes
import com.example.quoteofday.data.local.RoomFunctions
import com.example.quoteofday.data.models.QuotesType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChooseCategoriesViewModel @Inject constructor(
    private val roomFunctions: RoomFunctions
) : ViewModel() {

    suspend fun replaceType(selectedCategory: MutableList<QuotesType>) {

        val updatedQuotes = ListOfQuotes.allQuotes.map { quote ->
            val updatedType =
                quote.type.copy(isSelected = selectedCategory.any { it.name == quote.type.name && it.isSelected })
            quote.copy(type = updatedType)
        }

        updatedQuotes.forEach {
            roomFunctions.updateQuotes(it)
            roomFunctions.updateQuotesType(it.type)
        }
    }
}