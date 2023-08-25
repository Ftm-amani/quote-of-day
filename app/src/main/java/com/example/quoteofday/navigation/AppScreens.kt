package com.example.quoteofday.navigation

import java.lang.IllegalArgumentException

enum class AppScreens {
	DialogScreen,
	HomeScreen,
	FavoriteScreen,
	SettingScreen,
	ChangeWallpaperScreen;
	
	companion object {
		fun fromRoute(route: String?): AppScreens
		= when (route?.substringBefore("/")) {
			HomeScreen.name -> HomeScreen
			DialogScreen.name -> DialogScreen
			FavoriteScreen.name -> FavoriteScreen
			SettingScreen.name -> SettingScreen
			ChangeWallpaperScreen.name -> ChangeWallpaperScreen
			null -> HomeScreen
			else -> throw IllegalArgumentException("Route $route is not recognized")
		}
	}
}