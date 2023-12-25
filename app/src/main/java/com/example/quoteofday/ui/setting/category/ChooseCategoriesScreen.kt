package com.example.quoteofday.ui.setting.category

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.quoteofday.data.QuotesTypeName
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quoteofday.data.models.QuotesType
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
    val selectedCategories = mutableListOf<QuotesType>()
    val categories = QuotesTypeName.values().toList() // List of all available categories

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
            itemsIndexed (categories) { index,category ->
                val checked = remember { mutableStateOf(false) }

                Column {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Checkbox(
                            checked = checked.value,
                            onCheckedChange = { isChecked ->
                                checked.value = isChecked
                                val quoteType = QuotesType(index, category, checked.value)
                                if (isChecked) {
                                    selectedCategories.add(quoteType)
                                } else {
                                    selectedCategories.removeIf {
                                        it.name == quoteType.name
                                    }
                                }
                            },
                        )
                        Text(
                            modifier = Modifier.padding(start = 2.dp),
                            text = category.name
                        )
                    }
                }
            }
        }

        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.replaceType(selectedCategories)
            }
            navController.navigate(AppScreens.HomeScreen.name)
        }) {
            Text("Save")
        }
    }
}