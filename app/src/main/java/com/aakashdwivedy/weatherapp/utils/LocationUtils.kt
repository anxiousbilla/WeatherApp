package com.aakashdwivedy.weatherapp.utils

import android.os.Looper
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult

fun startLocationUpdates(
    fusedLocationProvider: FusedLocationProviderClient?,
    context: android.content.Context,
    onLocationUpdate: (Double, Double) -> Unit
) {
    val locationRequest = LocationRequest.create().apply {
        interval = 30000
        fastestInterval = 10000
        priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
    }

    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult.locations.lastOrNull()?.let { location ->
                val latitude = location.latitude
                val longitude = location.longitude
                onLocationUpdate(latitude, longitude)
            }
        }
    }

    try {
        fusedLocationProvider?.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    } catch (_: SecurityException) {
//        Toast.makeText(context, "Location permission not granted", Toast.LENGTH_SHORT).show()
    }
}