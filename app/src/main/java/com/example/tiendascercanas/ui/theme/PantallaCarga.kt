package com.example.tiendascercanas.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tiendascercanas.R
import kotlinx.coroutines.delay

@Composable
fun PantallaCarga(onFinish: () -> Unit) {

    val scale = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        // Animación del logo
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 900,
                easing = LinearOutSlowInEasing
            )
        )

        delay(1500)
        onFinish()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF6F2CC4)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logotienda),
            contentDescription = "Logo",
            modifier = Modifier
                .size(160.dp)
                .scale(scale.value)
                .clip(CircleShape)
        )
    }
}
