package com.example.quoteofday.ui

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.example.quoteofday.TAG
import com.example.quoteofday.navigation.AppNavigation
import com.example.quoteofday.ui.theme.QuoteOfDayTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
//HiltAndroidApp
class MainActivity : ComponentActivity() {
    
    private lateinit var sharedPreferences: SharedPreferences

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(ExperimentalComposeUiApi::class, ExperimentalPermissionsApi::class)
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
                        val postNotificationPermission =
                            rememberPermissionState(
                                permission = Manifest.permission.POST_NOTIFICATIONS
                            )

                        LaunchedEffect(key1 = true) {
                            if (!postNotificationPermission.status.isGranted) {
                                postNotificationPermission.launchPermissionRequest()
                            }
                            else {
                                Log.i(TAG, "permission granted: ")
                            }
                        }
                        val showDialog = !sharedPreferences.contains("user_name")
                        AppNavigation(showDialog,sharedPreferences)
                    }
                }
            }
        }
    }
}