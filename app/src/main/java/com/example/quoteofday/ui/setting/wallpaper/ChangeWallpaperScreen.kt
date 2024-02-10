package com.example.quoteofday.ui.setting.wallpaper

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.quoteofday.components.AppBar
import com.example.quoteofday.components.QodBackground
import com.example.quoteofday.navigation.AppScreens
import com.example.quoteofday.ui.theme.Green10
import com.example.quoteofday.ui.theme.Green40
import com.example.quoteofday.ui.theme.QuoteOfDayTheme
import kotlinx.coroutines.launch

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
    val scope = rememberCoroutineScope()
    var selectedBackgroundIndex by remember { mutableStateOf(-1) }
    val backgroundManager = BackgroundManager(LocalContext.current)

    QuoteOfDayTheme {
        QodBackground(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                contentColor = MaterialTheme.colorScheme.background,
                topBar = {
                    AppBar(
                        imageVector = Icons.Default.ArrowBack,
                        title = "Choose Background",
                        onNavigationIconClick = {
                            scope.launch {
                                navController.navigate(AppScreens.SettingScreen.name)
                            }
                        }
                    )
                },
            ) { paddingValue ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = paddingValue.calculateTopPadding(),
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 16.dp
                        ),
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        itemsIndexed(backgroundOptions) { index, item ->
                            BackgroundItems(
                                item = item,
                                isSelected = index == selectedBackgroundIndex,
                                onItemSelected = {
                                    selectedBackgroundIndex = index
                                },
                            )
                        }
                    }

                    if (selectedBackgroundIndex != -1) {
                        Button(
                            onClick = {
                                backgroundManager.saveBackgroundIndex(selectedBackgroundIndex)
                                navController.navigate(AppScreens.HomeScreen.name)
                            },
                            modifier = Modifier
                                .padding(vertical = 20.dp)
                                .align(Alignment.BottomCenter)
                        ) {
                            Text(text = "Save Changes")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BackgroundItems(
    item: Int,
    isSelected: Boolean,
    onItemSelected: () -> Unit
) {
    val borderColor = if (isSystemInDarkTheme()) Green40 else Green10
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                onItemSelected()
            }
            .border(
                width = if (isSelected) 4.dp else 0.dp,
                color = borderColor,
                shape = RoundedCornerShape(8.dp)
            )
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