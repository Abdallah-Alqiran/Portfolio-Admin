package com.alqiran.portfoliomainadmin.ui.components

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp


@Composable
fun HeadlineTextWidget(text: String) {

    val visible = remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = visible.value, label = "HeadlineTransition")

    val alpha by transition.animateFloat (
        label = "FadeIn",
        transitionSpec = { tween(durationMillis = 800) }
    ) { isVisible ->
        if (isVisible) 1f else 0f
    }

    val offsetY by transition.animateDp (
        label = "slideUp",
        transitionSpec = {tween(durationMillis = 800)}
    ) { isVisible ->
        if (isVisible) 0.dp else 20.dp
    }

    LaunchedEffect(Unit) {
        visible.value = true
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .padding(bottom = 8.dp)
            .graphicsLayer {
                this.alpha = alpha
                this.translationY = offsetY.toPx()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(6.dp))
        Box(modifier = Modifier
            .width(50.dp)
            .height(3.dp)
            .clip(RoundedCornerShape(50))
            .background(
                Brush.horizontalGradient(
                    listOf(
                        MaterialTheme.colorScheme.secondaryContainer,
                        MaterialTheme.colorScheme.tertiary,
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.secondary,
                    )
                )
            )
        ) {


        }
    }

}