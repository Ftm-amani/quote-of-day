package com.example.quoteofday.ui.setting.wallpaper

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quoteofday.R
import com.example.quoteofday.navigation.AppScreens

val backgroundOptions = listOf(
    R.drawable.back,
    R.drawable.back1,
    R.drawable.back2,
    R.drawable.back3,
    R.drawable.back4,
)
@Composable
fun ChangeWallpaperScreen(
    navController: NavController,
) {
    
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Choose Background",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                ) {
            itemsIndexed(backgroundOptions){ index, item ->
                BackgroundItems(item = item, index = index, navController = navController )
                
            }
        }
    }
}

@Composable
fun BackgroundItems(item :Int, index: Int, navController: NavController){
    val backgroundManager = BackgroundManager(LocalContext.current)
    var selectedBackgroundIndex by remember { mutableStateOf(backgroundManager.getBackgroundIndex()) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                selectedBackgroundIndex = index
                backgroundManager.saveBackgroundIndex(selectedBackgroundIndex)
                navController.navigate(AppScreens.HomeScreen.name)
            }
    ) {
        Image(
            painter = painterResource(id = item),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
    }
}