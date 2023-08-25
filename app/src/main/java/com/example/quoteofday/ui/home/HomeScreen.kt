package com.example.quoteofday.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quoteofday.data.models.Quotes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.quoteofday.data.models.MenuItem
import com.example.quoteofday.ui.MainViewModel
import com.example.quoteofday.ui.favotire.FavoritesViewModel
import com.example.quoteofday.ui.setting.wallpaper.BackgroundManager
import com.example.quoteofday.ui.setting.wallpaper.backgroundOptions
import com.google.android.material.animation.AnimationUtils.lerp
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@SuppressLint("RestrictedApi", "UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuoteScreen(
    navController: NavController,
    viewModel: MainViewModel,
    favoritesViewModel: FavoritesViewModel) {
    val quotes by viewModel.getAllRepos().collectAsState(initial = emptyList())
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0F
    ) {
        // provide pageCount
        quotes.size
    }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val backgroundManager = BackgroundManager(LocalContext.current)
    val selectedBackgroundIndex = backgroundManager.getBackgroundIndex()
    
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                imageVector = Icons.Default.Menu,
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                items = listOf(
                    MenuItem(
                        id = "HomeScreen",
                        title = "Home",
                        contentDescription = "Go to home screen",
                        icon = Icons.Default.Home,
                    ),
                    MenuItem(
                        id = "FavoriteScreen",
                        title = "favorite",
                        contentDescription = "Go to favorite screen",
                        icon = Icons.Default.Favorite
                    ),
                    MenuItem(
                        id = "SettingScreen",
                        title = "Setting",
                        contentDescription = "go to setting screen",
                        icon = Icons.Default.Settings
                    ),
                ),
                onItemClick = {
                            navController.navigate(it.id)
                }
            )
        }
    ) { paddingValue ->
        
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(
                    top = paddingValue.calculateTopPadding(),
                    start = 5.dp,
                    end = 5.dp,
                    bottom = 5.dp
                ),
            color = MaterialTheme.colors.background
        ) {
            if (quotes.isNotEmpty()) {
                VerticalPager(
                    state = pagerState,
                ) { page ->
                    QuoteItem(
                        quote = quotes[page],
                        modifier = Modifier
                            .pagerGateTransition(
                                page = page,
                                pagerState = pagerState
                            ),
                        backgroundIndex = selectedBackgroundIndex,
                        onFavoriteClick = {
                            favoritesViewModel.toggleQuoteFavorite(quotes[page])
                        },
                        onShareClick = {
                            val shareIntent = Intent(Intent.ACTION_SEND)
                            shareIntent.type = "text/plain"
                            shareIntent.putExtra(Intent.EXTRA_TEXT, quotes[page].text)
                            val chooser = Intent.createChooser(shareIntent, "Share Quote")
                            context.startActivity(chooser)
                        }
                    )
                }
            } else {
                Text(text = "No quotes available")
            }
        }
    }
}

@Composable
fun QuoteItem(
    quote: Quotes,
    modifier: Modifier,
    backgroundIndex: Int,
    onFavoriteClick: () -> Unit,
    onShareClick: () -> Unit) {
    
    val selectedBackgroundIndex by remember {
        mutableIntStateOf(backgroundIndex)
    }
    val backgroundImage: Painter = painterResource(backgroundOptions[selectedBackgroundIndex])
    
    Box(modifier.fillMaxWidth()) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = modifier.fillMaxHeight(),
            ) {
                
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .weight(1f, true)
                        .padding(vertical = 15.dp, horizontal = 30.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = quote.text,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(16.dp)
                            .wrapContentWidth()
                            .wrapContentHeight()
                    )
                }
                
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .padding(top = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Share,
                        contentDescription = "Share",
                        modifier = modifier
                            .padding(20.dp)
                            .size(48.dp)
                            .clickable {
                                onShareClick()
                            }
                    )
                    
                    Icon(
                        imageVector = if (quote.isFave == true) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = if (quote.isFave == true) "Unfavorite" else "Favorite",
                        modifier = modifier
                            .padding(20.dp)
                            .size(48.dp)
                            .clickable {
                                onFavoriteClick()
                            }
                    )
                }
                Spacer(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(20.dp)
                )
            }
        }
    }
}

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalFoundationApi::class)
fun Modifier.pagerGateTransition(page: Int, pagerState: PagerState) = graphicsLayer {
    val pageOffset = (
            (pagerState.currentPage - page) + pagerState
                .currentPageOffsetFraction
            ).absoluteValue
    
    alpha = lerp(
         0.5f,
         1f,
         1f - pageOffset.coerceIn(0f, 1f)
    )
}