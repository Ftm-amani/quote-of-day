package com.example.quoteofday.navigation

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quoteofday.ui.dialog.DialogScreen
import com.example.quoteofday.ui.MainViewModel
import com.example.quoteofday.ui.favotire.FavoriteScreen
import com.example.quoteofday.ui.favotire.FavoritesViewModel
import com.example.quoteofday.ui.home.QuoteScreen
import com.example.quoteofday.ui.setting.SettingsScreen
import com.example.quoteofday.ui.setting.category.ChooseCategoriesViewModel
import com.example.quoteofday.ui.setting.category.ChooseCategoryScreen
import com.example.quoteofday.ui.setting.wallpaper.ChangeWallpaperScreen

@ExperimentalComposeUiApi
@Composable
fun AppNavigation(showDialog: Boolean, sharedPreferences: SharedPreferences) {
	val navController = rememberNavController()
	NavHost(navController = navController,
		startDestination = if (showDialog) AppScreens.DialogScreen.name else AppScreens.HomeScreen.name ){
		
		composable(AppScreens.DialogScreen.name) {
			val mainViewModel = hiltViewModel<MainViewModel>()
			DialogScreen(
				navController = navController,
				viewModel = mainViewModel,
				sharedPreferences = sharedPreferences
			)
		}
		composable(AppScreens.HomeScreen.name) {
			val mainViewModel = hiltViewModel<MainViewModel>()
			val favoritesViewModel = hiltViewModel<FavoritesViewModel>()
			QuoteScreen(
				navController = navController,
				viewModel = mainViewModel,
				favoritesViewModel = favoritesViewModel
			)
		}
		composable(AppScreens.FavoriteScreen.name) {
			val favoritesViewModel = hiltViewModel<FavoritesViewModel>()
			FavoriteScreen(
				navController = navController,
				viewModel = favoritesViewModel
			)
		}
		composable(AppScreens.SettingScreen.name) {
			SettingsScreen(
				navController = navController,
			)
		}
		composable(AppScreens.ChangeWallpaperScreen.name) {
			ChangeWallpaperScreen(
				navController = navController,
			)
		}
		composable(AppScreens.ChooseCategoryScreen.name) {
			val chooseCategoriesViewModel = hiltViewModel<ChooseCategoriesViewModel>()

			ChooseCategoryScreen(
				navController = navController,
				viewModel = chooseCategoriesViewModel
			)
		}
	}
}

