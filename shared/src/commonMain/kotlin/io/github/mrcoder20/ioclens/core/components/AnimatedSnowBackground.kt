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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

private data class SnowParticle(
    val xFraction: Float,
    val initialYFraction: Float,
    val radiusDp: Float,
    val alpha: Float,
    val speedFactor: Float
)

@Composable
fun AnimatedSnowBackground(
    isDark: Boolean,
    modifier: Modifier = Modifier,
    particleCount: Int = 30
) {
    val particles = remember {
        val random = Random(42)
        List(particleCount) {
            SnowParticle(
                xFraction = random.nextFloat(),
                initialYFraction = random.nextFloat(),
                radiusDp = random.nextFloat() * 2.5f + 1.5f,
                alpha = random.nextFloat() * 0.5f + 0.25f,
                speedFactor = random.nextFloat() * 0.6f + 0.7f
            )
        }
    }

    val infiniteTransition = rememberInfiniteTransition()
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(18000, easing = LinearEasing), // Slower, calm, relaxing snowfall
            repeatMode = RepeatMode.Restart
        )
    )

    val snowColor = if (isDark) Color(0xFF00D2FF) else Color(0xFF00A8E8)

    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        particles.forEach { particle ->
            val x = particle.xFraction * w
            val rawY = (particle.initialYFraction + progress * particle.speedFactor) % 1f
            val y = rawY * h
            val radiusPx = particle.radiusDp * density

            drawCircle(
                color = snowColor.copy(alpha = particle.alpha),
                radius = radiusPx,
                center = Offset(x, y)
            )
        }
    }
}
