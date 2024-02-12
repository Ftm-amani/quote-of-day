package com.example.quoteofday.ui.addquote

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.quoteofday.TAG
import com.example.quoteofday.components.AppBar
import com.example.quoteofday.navigation.AppScreens
import com.example.quoteofday.ui.setting.category.ChooseCategoriesViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddQuoteScreen(
    navController: NavController,
    viewModel: AddQuoteViewModel,
    chooseCategoriesViewModel: ChooseCategoriesViewModel
) {

    val distinctQuotes by chooseCategoriesViewModel
        .distinctQuoteNamesAndSelection
        .collectAsState(emptyList())
    val scope = rememberCoroutineScope()
    var quoteText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }
    val shape = if (expanded) RoundedCornerShape(8.dp).copy(
        bottomEnd = CornerSize(0.dp),
        bottomStart = CornerSize(0.dp)
    )
    else RoundedCornerShape(8.dp)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = MaterialTheme.colorScheme.background,
        topBar = {
            AppBar(
                imageVector = Icons.Default.ArrowBack,
                title = "Add Quote",
                onNavigationIconClick = {
                    scope.launch {
                        navController.navigate(AppScreens.HomeScreen.name)
                    }
                }
            )
        },
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValue.calculateTopPadding(),
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
        ) {
            TextField(
                value = quoteText,
                onValueChange = {
                    quoteText = it
                },
                label = { Text("Enter your quote", fontWeight = FontWeight.Bold) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = shape,
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
            ) {

                TextField(
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    readOnly = true,
                    value = selectedOptionText,
                    onValueChange = {},
                    label = { Text("Choose Category", fontWeight = FontWeight.Bold) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    shape = shape,
                    colors = ExposedDropdownMenuDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    distinctQuotes.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption.quote_name) },
                            onClick = {
                                selectedOptionText = selectionOption.quote_name
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
                    }
                }
            }

            Button(
                onClick = {
                },
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Save Changes")
            }
        }
    }
}