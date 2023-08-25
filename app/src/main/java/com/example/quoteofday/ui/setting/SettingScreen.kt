package com.example.quoteofday.ui.setting

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
        content = { paddingValue ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValue.calculateTopPadding(),
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                
                ) {
                    // Night Mode Section
                    SettingsSectionCard(
                        title = "Night Mode",
                        hasToggle = true,
                        toggleState = darkMode
                    ) {
                    
                    }
                    // Quote Wallpaper Section
                    SettingsSectionCard(
                        title = "Quote Wallpaper",
                        hasToggle = false,
                        toggleState = false
                    ) {
                        SettingsSubSectionCard(
                            title = "Change wallpaper",
                            onItemClick = {
                            
                            })
                        
                    }
                    // Font Section
                    SettingsSectionCard(title = "Font", hasToggle = false, toggleState = false) {
                        SettingsSubSectionCard(
                            title = "Size",
                            onItemClick = {
                            
                            })
                        Divider()
                        SettingsSubSectionCard(
                            title = "Font",
                            onItemClick = {
                            
                            })
                        Divider()
                        SettingsSubSectionCard(
                            title = " Font Color",
                            onItemClick = {
                            
                            })
                    }
                }
                
            }
        }
    )
}

@Composable
fun SettingsSectionCard(
    title: String,
    hasToggle: Boolean,
    toggleState: Boolean,
    content: @Composable () -> Unit
) {
    var toggle by remember { mutableStateOf(toggleState) }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        elevation = CardDefaults.cardElevation(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                if (hasToggle) {
                    Switch(
                        checked = toggle,
                        onCheckedChange = { toggle = it },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }
            if (!hasToggle) {
                Divider()
                content()
            }
        }
    }
}

@Composable
fun SettingsSubSectionCard(title: String, onItemClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "go to $title setting"
        )
    }
}