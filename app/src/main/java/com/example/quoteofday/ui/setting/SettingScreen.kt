package com.example.quoteofday.ui.setting

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quoteofday.navigation.AppScreens
import com.example.quoteofday.components.AppBar
import com.example.quoteofday.components.QodBackground
import com.example.quoteofday.ui.theme.LocalBackgroundTheme
import com.example.quoteofday.ui.theme.QuoteOfDayTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(navController: NavController) {

    var fontSizeSettingExpand by remember { mutableStateOf(false) }
    var fontColorSettingExpand by remember { mutableStateOf(false) }
    var fontSettingExpand by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                imageVector = Icons.Default.ArrowBack,
                title = "Setting",
                onNavigationIconClick = {
                    scope.launch {
                        navController.navigate(AppScreens.HomeScreen.name)
                    }
                }
            )
        },
        content = { paddingValue ->
            QuoteOfDayTheme {
                QodBackground(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = paddingValue.calculateTopPadding(),
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState())

                    ) {
                        // Quote Wallpaper Section
                        SettingsSectionCard(
                            title = "Quote Wallpaper",
                        ) {
                            SettingsSubSectionCard(
                                title = "Change wallpaper",
                                isExpandedInitially = false,
                                onItemClick = {
                                    navController.navigate(AppScreens.ChangeWallpaperScreen.name)
                                }
                            ) {}
                        }
                        // Font Section
                        SettingsSectionCard(
                            title = "Font",
                        ) {
                            SettingsSubSectionCard(
                                title = "Size",
                                isExpandedInitially = fontSizeSettingExpand,
                                onItemClick = {
                                    fontSizeSettingExpand = !fontSizeSettingExpand
                                }) {
                                FontSizeSettingContent()
                            }
                            Divider()
                            SettingsSubSectionCard(
                                title = "Font",
                                isExpandedInitially = fontSettingExpand,
                                onItemClick = {
                                    fontSettingExpand = !fontSettingExpand
                                }) {
                                FontFamilySettingContent()
                            }
                            Divider()
                            // select color Section
                            SettingsSubSectionCard(
                                title = "Text Color",
                                isExpandedInitially = fontColorSettingExpand,
                                onItemClick = {
                                    fontColorSettingExpand = !fontColorSettingExpand
                                }) {
                                ColorPickerBottomSheet(
                                    onDismiss = {
                                        navController.navigate(AppScreens.HomeScreen.name)
                                    })
                            }
                        }
                        //Category Section
                        SettingsSectionCard(
                            title = "Category",
                        ) {
                            SettingsSubSectionCard(
                                title = "Choose Categories",
                                isExpandedInitially = false,
                                onItemClick = {
                                    navController.navigate(AppScreens.ChooseCategoryScreen.name)
                                }
                            ) {}
                        }
                    }
                }
                }
            }
        }
    )
}

@Composable
fun SettingsSectionCard(
    title: String,
    content: @Composable () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 16.dp),
        elevation = CardDefaults.cardElevation(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            Divider()
            content()
        }
    }
}

@Composable
fun SettingsSubSectionCard(
    title: String,
    isExpandedInitially: Boolean,
    onItemClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { onItemClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "go to $title setting"
            )

        }

        if (isExpandedInitially) {
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FontSizeSettingContent() {
    val interactionSource = remember { MutableInteractionSource() }
    val fontManager = FontManager(LocalContext.current)
    val fontSize = fontManager.getFontSize().toFloat()
    var sliderPosition by remember { mutableFloatStateOf(fontSize) }

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Text(
            text = sliderPosition.toInt().toString(),
            fontWeight = FontWeight.Bold
        )
        Slider(
            modifier = Modifier.semantics { contentDescription = "font size" },
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 25f..40f,
            steps = 2,
            interactionSource = interactionSource,
            onValueChangeFinished = {
                fontManager.saveFontSize(sliderPosition.toInt())
            },
            thumb = {
                SliderDefaults.Thumb(
                    interactionSource = interactionSource,
                    thumbSize = DpSize(20.dp, 20.dp)
                )
            },
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FontFamilySettingContent() {
    val fonts = listOf(
        FontFamily.Serif.name,
        FontFamily.SansSerif.name,
        FontFamily.Cursive.name,
        FontFamily.Monospace.name,
    )

    val fontManager = FontManager(LocalContext.current)
    val currentFont = fontManager.getFontFamily()
    var selectedFont by remember { mutableStateOf(currentFont) }
    val itemBorderColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    FlowRow(
        modifier = Modifier,
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.Center,
        maxItemsInEachRow = fonts.size,
    ) {
        fonts.forEach { font ->
            val color = LocalBackgroundTheme.current.color

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        selectedFont = font
                        fontManager.saveFontFamily(font)
                    },
            ) {
                Text(
                    text = font,
                    modifier =
                    if (font == selectedFont) {
                        Modifier
                            .border(
                                width = 1.dp,
                                color = itemBorderColor,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .background(
                                color = if (color == Color.Unspecified) Color.Transparent
                                else color,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(8.dp)
                    } else {
                        Modifier
                            .background(
                                if (color == Color.Unspecified) Color.Transparent
                                else color,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(8.dp)
                    },
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }
    }
}