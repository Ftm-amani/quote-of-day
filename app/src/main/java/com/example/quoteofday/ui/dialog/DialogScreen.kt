package com.example.quoteofday.ui.dialog

import android.content.SharedPreferences
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quoteofday.navigation.AppScreens
import com.example.quoteofday.ui.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun DialogScreen(
    navController: NavController,
    viewModel: MainViewModel,
    sharedPreferences: SharedPreferences
) {
    
    val userNameEntered = sharedPreferences.contains("user_name")
    
    if (!userNameEntered) {
        var textValue by remember { mutableStateOf("") }
        
        AlertDialog(
            shape = RoundedCornerShape(5.dp),
            onDismissRequest = { },
            title = {
                Text(
                    modifier = Modifier.padding(bottom = 20.dp),
                    text = "What should I call you?"
                )
            },
            text = {
                OutlinedTextField(
                    modifier = Modifier.wrapContentHeight(),
                    value = textValue,
                    onValueChange = { textValue = it })
            },
            dismissButton = {
                Button(onClick = { /* toDo something else */ }) {
                    Text("Cancel")
                }
            },
            confirmButton = {
                Button(onClick = {
                    sharedPreferences.edit().putString("user_name", textValue).apply()
                    
                    navController.navigate(AppScreens.HomeScreen.name)
                    
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.insert()
                    }
                }) {
                    Text("Next")
                }
            }
        )
    } else {
        // If userName has been entered before, navigate directly to HomeScreen
        navController.navigate(AppScreens.HomeScreen.name)
    }
}