package com.example.quoteofday.ui.setting

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quoteofday.navigation.AppScreens
import com.example.quoteofday.ui.home.AppBar
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(navController: NavController) {
    var darkMode by remember { mutableStateOf(false) }
    var selectedFont by remember { mutableStateOf("Font 1") }
    val scope = rememberCoroutineScope()
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(
                imageVector = Icons.Default.ArrowBack,
                onNavigationIconClick = {
                    scope.launch {
                        navController.navigate(AppScreens.HomeScreen.name)
                    }
                }
            )
        },
        content = {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Dark Mode",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.weight(1f)
                        )
                        Switch(
                            checked = darkMode,
                            onCheckedChange = { darkMode = it }
                        )
                    }
                    
                    // todo: Create dropdown menu for fonts here
                    
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Background",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.weight(1f)
                        )
                        Button(
                            onClick = { /* todo Open background picker or perform relevant action */ },
                        ) {
                            Text(text = "Change Background")
                        }
                    }
                }
            }
        }
    )
}