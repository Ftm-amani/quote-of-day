package com.example.quoteofday.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quoteofday.ui.DialogScreen
import com.example.quoteofday.ui.MainViewModel
import com.example.quoteofday.ui.QuoteScreen

@ExperimentalComposeUiApi
@Composable
fun AppNavigation() {
	val navController = rememberNavController()
	NavHost(navController = navController,
			startDestination = AppScreens.DialogScreen.name ){
		
		composable(AppScreens.DialogScreen.name) {
			val mainViewModel = hiltViewModel<MainViewModel>()
			DialogScreen(
				navController = navController,
				viewModel = mainViewModel
			)
		}
		composable(AppScreens.HomeScreen.name) {
			val mainViewModel = hiltViewModel<MainViewModel>()
			QuoteScreen(
				navController = navController,
				viewModel = mainViewModel
			)
		}
	}

}