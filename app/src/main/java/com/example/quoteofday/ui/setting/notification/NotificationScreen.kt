package com.example.quoteofday.ui.setting.notification

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quoteofday.components.AppBar
import com.example.quoteofday.components.QodBackground
import com.example.quoteofday.navigation.AppScreens
import com.example.quoteofday.ui.theme.QuoteOfDayTheme
import com.example.quoteofday.components.notification.QuoteNotificationService
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("MutableCollectionMutableState")
@Composable
fun NotificationScreen(
    navController: NavController,
    viewModel: NotificationViewModel
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    //timeState holds time that user picks
    val timeState = rememberTimePickerState(11, 30, false)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = MaterialTheme.colorScheme.background,
        topBar = {
            AppBar(
                imageVector = Icons.Default.ArrowBack,
                title = "Set Notification",
                onNavigationIconClick = {
                    scope.launch {
                        navController.navigate(AppScreens.SettingScreen.name)
                    }
                }
            )
        },
        content = { paddingValue ->
            QuoteOfDayTheme {
                QodBackground(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                top = paddingValue.calculateTopPadding(),
                                start = 16.dp,
                                end = 16.dp,
                                bottom = 16.dp
                            ),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            // TimePicker for selecting the notification time
                            TimePicker(
                                state = timeState,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 16.dp),
                                colors = TimePickerDefaults.colors(),
                                layoutType = TimePickerDefaults.layoutType(),
                            )

                            Button(
                                onClick = {
                                    val quoteNotificationService = QuoteNotificationService(context)
                                    val selectedHour = timeState.hour
                                    val selectedMinute = timeState.minute
                                    quoteNotificationService.scheduleNotification(
                                        selectedHour,
                                        selectedMinute
                                    )

                                    navController.navigate(AppScreens.SettingScreen.name)
                                },
                                modifier = Modifier
                                    .padding(vertical = 20.dp)
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                Text(text = "Save Notification Settings")
                            }
                        }
                    }
                }
            }
        }
    )
}