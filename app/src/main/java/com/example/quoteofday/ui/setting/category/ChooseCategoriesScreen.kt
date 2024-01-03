package com.example.quoteofday.ui.setting.category

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quoteofday.data.models.*
import com.example.quoteofday.navigation.AppScreens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("MutableCollectionMutableState")
@Composable
fun ChooseCategoryScreen(
    navController: NavController,
    viewModel: ChooseCategoriesViewModel,
) {
    val distinctQuotes by viewModel.distinctQuoteNamesAndSelection.collectAsState(emptyList())
    val selectedCategories = mutableListOf<QuoteNameAndSelection>()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Choose what kind of Quotes you wanna see",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(10.dp)
        )

        LazyColumn {
            items(distinctQuotes) { category ->
                val checked = remember { mutableStateOf(category.is_selected) }

                Column {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Checkbox(
                            checked = checked.value,
                            onCheckedChange = { isChecked ->
                                checked.value = isChecked
                                val quoteType =
                                    QuoteNameAndSelection(category.quote_name, checked.value)
                                if (isChecked) {
                                    if (!selectedCategories.contains(quoteType)) selectedCategories
                                        .add(quoteType)
                                } else {
                                    if (!selectedCategories.contains(quoteType)) selectedCategories
                                        .add(quoteType)
                                }
                            },
                        )
                        Text(
                            modifier = Modifier.padding(start = 2.dp),
                            text = category.quote_name
                        )
                    }
                }
            }
        }

        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.updateQuotesByType(selectedCategories)
            }
            navController.navigate(AppScreens.HomeScreen.name)
        }) {
            Text("Save")
        }
    }
}