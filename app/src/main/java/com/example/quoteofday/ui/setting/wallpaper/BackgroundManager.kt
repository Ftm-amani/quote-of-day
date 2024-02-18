package com.example.quoteofday.ui.setting.wallpaper

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class BackgroundManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("background_prefs", Context.MODE_PRIVATE)

    fun saveBackgroundIndex(index: Int) {
        sharedPreferences.edit {
            putInt("background_index", index)
        }
    }

    fun getBackgroundIndex(): Int {
        return sharedPreferences.getInt("background_index", 0)
    }
}