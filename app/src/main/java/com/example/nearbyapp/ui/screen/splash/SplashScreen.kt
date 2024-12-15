package com.example.nearbyapp.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nearbyapp.R
import com.example.nearbyapp.ui.theme.GreenLight
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(modifier: Modifier = Modifier, onNavigateToWelcome: () -> Unit) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    LaunchedEffect(key1 = Unit) {
        delay(3_000)
        onNavigateToWelcome()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(GreenLight)
    ) {
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.img_logo_logo_logo_text),
            contentDescription = "Logo"
        )
        Image(
            modifier = Modifier.align(Alignment.BottomCenter),
            painter = painterResource(id = R.drawable.bg_splash_screen),
            contentDescription = "Imagem de fundo"
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(screenHeight / 3)
                .background(
                    brush = Brush.verticalGradient(
                        0.0f to GreenLight,
                        0.2f to GreenLight,
                        1.0f to Color.Transparent
                    )
                )
                .align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreen(onNavigateToWelcome = {})
}