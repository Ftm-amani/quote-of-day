package com.example.quoteofday.ui.favotire

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quoteofday.data.models.Quotes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(navController: NavController, viewModel: FavoritesViewModel) {
    
    val favoriteQuotes by viewModel.favoriteQuotes.collectAsState(initial = emptyList())
    
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Favorite Quotes",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            FavoriteQuotesGrid(favoriteQuotes)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteQuotesGrid(quotesList: List<Quotes>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        items(quotesList) { quote ->
            FavoriteQuoteItem(quote)
        }
    }
}

@Composable
fun FavoriteQuoteItem(quote: Quotes) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = quote.text,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = quote.type.toString(),
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}