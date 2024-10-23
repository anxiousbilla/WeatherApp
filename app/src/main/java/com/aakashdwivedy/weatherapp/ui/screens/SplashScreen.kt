package com.aakashdwivedy.weatherapp.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.aakashdwivedy.weatherapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(modifier: Modifier, finishAnimation: () -> Unit) {
    var startAnimation by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(
        targetValue = if (startAnimation) 0.1f else 1f,
        animationSpec = tween(durationMillis = 900),
        label = "alpha value of splash icon"
    )
    val scale by animateFloatAsState(
        targetValue = if (startAnimation) 0.5f else 1f,
        animationSpec = tween(durationMillis = 900),
        label = "scale value of splash icon"
    )

    LaunchedEffect(Unit) {
        delay(200)
        startAnimation = true
    }

    if (alpha == 0.1F) finishAnimation()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black,
                        Color.DarkGray,
                        Color.Black
                    )
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .scale(scale)
                .alpha(alpha),
            painter = painterResource(R.drawable.circular_app_icon),
            contentDescription = "Splash logo"
        )
    }
}