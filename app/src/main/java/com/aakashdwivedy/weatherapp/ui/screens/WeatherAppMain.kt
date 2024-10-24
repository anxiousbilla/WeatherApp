package com.aakashdwivedy.weatherapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient

@Composable
fun WeatherAppMain(modifier: Modifier, fusedLocationProvider: FusedLocationProviderClient?) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = ScreenNames.HomeScreen.name
    ) {
        composable(route = ScreenNames.HomeScreen.name) {
            HomeScreen(navController = navController, fusedLocationProvider)
        }
    }
}