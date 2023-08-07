package com.example.quoteofday.ui

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quoteofday.navigation.AppScreens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
@Composable
fun DialogScreen(
    navController : NavController,
    viewModel: MainViewModel
) {
    
    var textValue by remember { mutableStateOf("") }
    
    AlertDialog(
        onDismissRequest = { },
        shape = RoundedCornerShape(5.dp),
        title = { Text(modifier = Modifier.padding(bottom = 20.dp),
            text = "What should I call you?") },
        text = {
                OutlinedTextField(
                    modifier = Modifier.wrapContentHeight(),
                    value = textValue,
                    onValueChange = { textValue = it })
        } ,
        confirmButton = {
            Button(onClick = {
            /* toDo something with the entered text value */
                navController.navigate(AppScreens.HomeScreen.name)
                
                CoroutineScope(Dispatchers.IO)
                    .launch {
                        viewModel.insert()
                    }
            }) {
                Text("Next")
            }
        },
        dismissButton = {
            Button(onClick = { /* toDo something else */ }) {
                Text("Cancel")
            }
        }
    )
    
  
    
}


@Preview
@Composable
fun ShowDialogQuestionPrew() {
//    DialogScreen()
}