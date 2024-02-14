package com.example.quoteofday.ui.setting.notification

import androidx.lifecycle.ViewModel
import com.example.quoteofday.data.local.RoomFunctions
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val roomFunctions: RoomFunctions
) : ViewModel() {}