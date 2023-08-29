package com.example.quoteofday.ui.setting

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class FontManager (context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("font_prefs", Context.MODE_PRIVATE)
    
    fun saveFontSize(size: Int) {
        sharedPreferences.edit {
            putInt("font_size", size)
        }
    }
    
    fun getFontSize(): Int {
        return sharedPreferences.getInt("font_size", 30)
    }
}