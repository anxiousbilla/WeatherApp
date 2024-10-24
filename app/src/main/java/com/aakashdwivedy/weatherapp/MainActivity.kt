package com.aakashdwivedy.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.aakashdwivedy.weatherapp.ui.screens.WeatherAppMain
import com.aakashdwivedy.weatherapp.ui.screens.SplashScreen
import com.aakashdwivedy.weatherapp.ui.theme.WeatherAppTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : ComponentActivity() {
    private var fusedLocationProvider: FusedLocationProviderClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        fusedLocationProvider = LocationServices.getFusedLocationProviderClient(this)

        setContent {
            WeatherAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    var isShowSplash by remember { mutableStateOf(true) }
                    if (isShowSplash) {
                        SplashScreen(modifier = Modifier.padding(it)) { isShowSplash = false }
                    } else {
                        WeatherAppMain(modifier = Modifier.padding(it), fusedLocationProvider)
                    }
                }
            }
        }
    }
}