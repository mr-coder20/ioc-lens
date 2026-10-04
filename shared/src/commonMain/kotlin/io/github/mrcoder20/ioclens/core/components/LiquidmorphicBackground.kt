package io.github.mrcoder20.ioclens.core.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.mrcoder20.ioclens.core.theme.PrimaryCyan
import io.github.mrcoder20.ioclens.core.theme.SecondaryTeal
import kotlin.math.cos
import kotlin.math.sin

/**
 * Ultra-Modern Liquidmorphism Background Component for Compose Multiplatform.
 * Renders floating, morphing neon liquid blobs with continuous sine/cosine trajectory curves.
 */
@Composable
fun LiquidmorphicBackground(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition()

    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2f * kotlin.math.PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(14000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Blob 1: Cyan Liquid Spot (Top Left)
        val blob1X = w * 0.3f + sin(progress) * 80.dp.toPx()
        val blob1Y = h * 0.25f + cos(progress * 0.8f) * 60.dp.toPx()
        val radius1 = w.coerceAtMost(h) * 0.45f

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    PrimaryCyan.copy(alpha = 0.25f),
                    PrimaryCyan.copy(alpha = 0.08f),
                    Color.Transparent
                ),
                center = Offset(blob1X, blob1Y),
                radius = radius1
            ),
            radius = radius1,
            center = Offset(blob1X, blob1Y)
        )

        // Blob 2: Electric Indigo Spot (Bottom Right)
        val blob2X = w * 0.7f + cos(progress * 1.2f) * 90.dp.toPx()
        val blob2Y = h * 0.7f + sin(progress * 0.9f) * 70.dp.toPx()
        val radius2 = w.coerceAtMost(h) * 0.5f

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    SecondaryTeal.copy(alpha = 0.22f),
                    SecondaryTeal.copy(alpha = 0.06f),
                    Color.Transparent
                ),
                center = Offset(blob2X, blob2Y),
                radius = radius2
            ),
            radius = radius2,
            center = Offset(blob2X, blob2Y)
        )
    }
}
