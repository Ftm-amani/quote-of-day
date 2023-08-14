package com.example.quoteofday.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.quoteofday.R
import com.google.android.material.animation.AnimationUtils.lerp
import kotlin.math.absoluteValue

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuoteScreen(navController: NavController, viewModel: MainViewModel) {
    val quotes by viewModel.getAllRepos().collectAsState(initial = emptyList())
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0F
    ) {
        // provide pageCount
        quotes.size
    }
    
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
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
                            pagerState = pagerState)
                )
            }
        } else {
            Text(text = "No quotes available")
        }
    }
}

@Composable
fun QuoteItem(quote: Quotes,
modifier: Modifier) {
    val backgroundImage: Painter = painterResource(R.drawable.back)
    
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
                    )
                    
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        modifier = modifier
                            .padding(20.dp)
                            .size(48.dp)
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