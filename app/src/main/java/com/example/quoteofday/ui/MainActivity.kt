package com.example.quoteofday.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.example.quoteofday.navigation.AppNavigation
import com.example.quoteofday.ui.theme.QuoteOfDayTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
//HiltAndroidApp
class MainActivity : ComponentActivity() {
    
    private lateinit var sharedPreferences: SharedPreferences
    
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    
        sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    
        setContent {
            setContent {
                QuoteOfDayTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        val showDialog = !sharedPreferences.contains("user_name")
                        AppNavigation(showDialog,sharedPreferences)
                    }
                }
            }
        }
    }
}