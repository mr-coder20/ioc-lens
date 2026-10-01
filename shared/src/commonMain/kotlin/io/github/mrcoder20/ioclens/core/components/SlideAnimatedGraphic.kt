package io.github.mrcoder20.ioclens.core.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SlideAnimatedGraphic(
    slideId: Int,
    emoji: String,
    modifier: Modifier = Modifier,
    size: Dp = 80.dp,
    emojiFontSize: Int = 36
) {
    val infiniteTransition = rememberInfiniteTransition()

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.85f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f))
            .border(2.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        // Animated Canvas Overlay Rings
        val primaryColor = MaterialTheme.colorScheme.primary
        val secondaryColor = MaterialTheme.colorScheme.secondary

        Canvas(modifier = Modifier.matchParentSize().rotate(rotation)) {
            val centerOffset = Offset(this.size.width / 2f, this.size.height / 2f)
            val maxR = this.size.width / 2f

            when (slideId) {
                1 -> {
                    // Scanning Radar Rings
                    drawCircle(
                        color = primaryColor.copy(alpha = 0.4f),
                        radius = maxR * 0.9f,
                        style = Stroke(width = 2.dp.toPx())
                    )
                    drawLine(
                        color = primaryColor,
                        start = centerOffset,
                        end = Offset(centerOffset.x + maxR * 0.85f, centerOffset.y),
                        strokeWidth = 3.dp.toPx()
                    )
                }
                2 -> {
                    // Security Shield Pulsing Aura
                    drawCircle(
                        color = secondaryColor.copy(alpha = 0.3f),
                        radius = maxR * pulseScale * 0.8f,
                        style = Stroke(width = 3.dp.toPx())
                    )
                }
                3 -> {
                    // Network Multi-Node Orbit
                    drawCircle(
                        color = primaryColor.copy(alpha = 0.5f),
                        radius = maxR * 0.75f,
                        style = Stroke(width = 1.5.dp.toPx())
                    )
                    drawCircle(
                        color = secondaryColor,
                        radius = 4.dp.toPx(),
                        center = Offset(centerOffset.x + maxR * 0.75f, centerOffset.y)
                    )
                }
                else -> {
                    // Lock Pulse Aura
                    drawCircle(
                        color = primaryColor.copy(alpha = 0.4f),
                        radius = maxR * pulseScale * 0.85f,
                        style = Stroke(width = 2.dp.toPx())
                    )
                }
            }
        }

        Text(
            text = emoji,
            fontSize = emojiFontSize.sp
        )
    }
}
