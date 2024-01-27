package com.example.quoteofday.ui.setting.category

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quoteofday.components.AppBar
import com.example.quoteofday.components.QodBackground
import com.example.quoteofday.data.models.*
import com.example.quoteofday.navigation.AppScreens
import com.example.quoteofday.ui.theme.LocalBackgroundTheme
import com.example.quoteofday.ui.theme.QuoteOfDayTheme
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
    val selectedCategories = remember { mutableStateListOf<QuoteNameAndSelection>() }
    val scope = rememberCoroutineScope()
    val color = LocalBackgroundTheme.current.color


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = MaterialTheme.colorScheme.background,
        topBar = {
            AppBar(
                imageVector = Icons.Default.ArrowBack,
                title = "Choose Categories",
                onNavigationIconClick = {
                    scope.launch {
                        navController.navigate(AppScreens.SettingScreen.name)
                    }
                }
            )
        },
    ) { paddingValue ->

        QuoteOfDayTheme {
            QodBackground(modifier = Modifier
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

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 8.dp),
                ) {
                    Text(
                        text = "Select All categories that interest you.",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Start
                    )
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(distinctQuotes) { category ->
                        val isSelected = remember { mutableStateOf(category.is_selected) }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .border(
                                    width = 2.dp,
                                    color = if (isSelected.value) Color.DarkGray else Color.LightGray,
                                    shape = RoundedCornerShape(8.dp),
                                )
                                .clickable {
                                    isSelected.value = !isSelected.value
                                    val quoteType =
                                        QuoteNameAndSelection(category.quote_name, isSelected.value)

                                    if (isSelected.value) {
                                        if (!selectedCategories.contains(quoteType)) selectedCategories
                                            .add(quoteType)
                                    } else {
                                        if (!selectedCategories.contains(quoteType)) selectedCategories
                                            .add(quoteType)
                                    }
                                },
                            backgroundColor = if (color == Color.Unspecified) Color.Transparent else color

                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    modifier = Modifier.padding(
                                        horizontal = 8.dp,
                                        vertical = 10.dp
                                    ),
                                    text = category.quote_name,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }

                Button(
                    modifier = Modifier.padding(10.dp),
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            viewModel.updateQuotesByType(selectedCategories)
                        }
                        navController.navigate(AppScreens.HomeScreen.name)
                    }) {
                    Text("Save")
                }
            }
        }}
    }
}