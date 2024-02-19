package com.example.quoteofday.ui.setting.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quoteofday.data.local.RoomFunctions
import com.example.quoteofday.components.notification.QuoteManager.randomQuoteNotification
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val roomFunctions: RoomFunctions
) : ViewModel() {
    init {
        viewModelScope.launch {
            getRandomQuote()
        }
    }
    private suspend fun getRandomQuote() {
       roomFunctions.getRandomQuote().collect{
           if (it.isNullOrEmpty()){
               randomQuoteNotification = "To be yourself in a world that is constantly trying to make you something else is the greatest accomplishment."
           }
           else{
               randomQuoteNotification = it
           }
       }
    }
}