package com.aakashdwivedy.weatherapp.ui.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.aakashdwivedy.weatherapp.ui.components.PermissionDeniedScreen
import com.aakashdwivedy.weatherapp.ui.components.WeatherHomeScreen
import com.aakashdwivedy.weatherapp.utils.startLocationUpdates
import com.google.android.gms.location.FusedLocationProviderClient

@Composable
fun HomeScreen(
    navController: NavHostController,
    fusedLocationProvider: FusedLocationProviderClient?
) {
    val context = LocalContext.current
    var latitude = remember { mutableStateOf<Double?>(null) }
    var longitude = remember { mutableStateOf<Double?>(null) }
    var permissionGranted = remember { mutableStateOf(false) }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            startLocationUpdates(fusedLocationProvider, context) { lat, long ->
                latitude.value = lat
                longitude.value = long
            }
            permissionGranted.value = true
        }
    }

    LaunchedEffect(Unit) {
        locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    if (permissionGranted.value) {
        WeatherHomeScreen()
    } else {
        PermissionDeniedScreen()
    }
}