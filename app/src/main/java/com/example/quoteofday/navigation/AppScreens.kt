package com.example.quoteofday.navigation

import java.lang.IllegalArgumentException

enum class AppScreens {
	DialogScreen,
	HomeScreen,
	ProfileScreen;
	
	companion object {
		fun fromRoute(route: String?): AppScreens
		= when (route?.substringBefore("/")) {
			HomeScreen.name -> HomeScreen
			DialogScreen.name -> DialogScreen
			ProfileScreen.name -> ProfileScreen
			null -> HomeScreen
			else -> throw IllegalArgumentException("Route $route is not recognized")
		}
	}
}