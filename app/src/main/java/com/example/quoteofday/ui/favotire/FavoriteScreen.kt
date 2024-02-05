package com.example.quoteofday.ui.favotire

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quoteofday.data.models.Quotes
import com.example.quoteofday.navigation.AppScreens
import com.example.quoteofday.components.AppBar
import kotlinx.coroutines.launch
import androidx.compose.material.Icon
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpOffset

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(navController: NavController, viewModel: FavoritesViewModel) {
    val scope = rememberCoroutineScope()
    val favoriteQuotes by viewModel.favoriteQuotes.collectAsState(initial = emptyList())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = MaterialTheme.colorScheme.background,
        topBar = {
            AppBar(
                imageVector = Icons.Default.ArrowBack,
                title = "Favorite Quotes",
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
            FavoriteQuotesGrid(favoriteQuotes, viewModel)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteQuotesGrid(quotesList: List<Quotes>, viewModel: FavoritesViewModel) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        items(quotesList) { quote ->
            FavoriteQuoteItem(quote, viewModel)
        }
    }
}

@Composable
fun FavoriteQuoteItem(quote: Quotes, viewModel: FavoritesViewModel) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        Row {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = quote.quoteText,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = quote.quoteName,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // Three dots icon for options
            IconButton(
                onClick = { expanded = true }
            ) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
            }

            // Dropdown menu for options
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                offset = DpOffset(
                    x=50.dp,
                    y= (-10).dp
                )
            ) {
                DropdownMenuItem(
                    text = {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Delete")
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(start = 20.dp)
                                    .align(Alignment.CenterVertically),
                                tint = Color.Red
                                )
                        }
                    },
                    onClick = {
                        expanded = false
                        // Handle delete option
                        viewModel.toggleQuoteFavorite(quote)
                    })
            }
        }
    }
}