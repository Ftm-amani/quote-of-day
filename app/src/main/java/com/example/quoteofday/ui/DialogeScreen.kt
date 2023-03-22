package com.example.quoteofday.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ShowDialogQuestion() {
    
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
    ShowDialogQuestion()
}