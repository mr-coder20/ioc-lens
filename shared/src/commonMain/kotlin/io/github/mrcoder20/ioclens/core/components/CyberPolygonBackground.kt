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
import io.github.mrcoder20.ioclens.core.theme.HighlightSkyBlue
import io.github.mrcoder20.ioclens.core.theme.PrimaryCyan
import io.github.mrcoder20.ioclens.core.theme.SecondaryTeal
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin
import kotlin.random.Random

private data class GridNode(
    val cellX: Int,
    val cellY: Int,
    val phaseX: Float,
    val phaseY: Float,
    val speedX: Float,
    val speedY: Float,
    val radius: Float
)

/**
 * Ultra-Futuristic Cyber Polygon Mesh & Fractured Line Background for Compose Multiplatform.
 * Uses a 5x5 grid spatial distribution for 100% uniform screen coverage and continuous sinusoidal orbits.
 */
@Composable
fun CyberPolygonBackground(
    modifier: Modifier = Modifier,
    cols: Int = 5,
    rows: Int = 5
) {
    val nodes = remember {
        val random = Random(42)
        val list = mutableListOf<GridNode>()
        for (c in 0 until cols) {
            for (r in 0 until rows) {
                list.add(
                    GridNode(
                        cellX = c,
                        cellY = r,
                        phaseX = random.nextFloat() * 2f * kotlin.math.PI.toFloat(),
                        phaseY = random.nextFloat() * 2f * kotlin.math.PI.toFloat(),
                        speedX = 0.4f + random.nextFloat() * 0.4f,
                        speedY = 0.4f + random.nextFloat() * 0.4f,
                        radius = 2.5f + random.nextFloat() * 2f
                    )
                )
            }
        }
        list
    }

    val infiniteTransition = rememberInfiniteTransition()
    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2f * kotlin.math.PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(22000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        val cellW = w / cols
        val cellH = h / rows
        val maxConnectDist = (cellW.coerceAtLeast(cellH) * 1.6f)

        val currentPoints = nodes.map { node ->
            val centerX = (node.cellX + 0.5f) * cellW
            val centerY = (node.cellY + 0.5f) * cellH

            val offsetX = sin(time * node.speedX + node.phaseX) * (cellW * 0.38f)
            val offsetY = cos(time * node.speedY + node.phaseY) * (cellH * 0.38f)

            Offset(centerX + offsetX, centerY + offsetY) to node.radius
        }

        // Draw Interconnecting Cyber Lines
        for (i in currentPoints.indices) {
            val (p1, _) = currentPoints[i]
            for (j in i + 1 until currentPoints.size) {
                val (p2, _) = currentPoints[j]
                val dist = hypot((p1.x - p2.x).toDouble(), (p1.y - p2.y).toDouble()).toFloat()

                if (dist < maxConnectDist) {
                    val lineAlpha = (1f - (dist / maxConnectDist)) * 0.65f
                    val lineColor = when ((i + j) % 3) {
                        0 -> PrimaryCyan
                        1 -> SecondaryTeal
                        else -> HighlightSkyBlue
                    }

                    drawLine(
                        color = lineColor.copy(alpha = lineAlpha),
                        start = p1,
                        end = p2,
                        strokeWidth = 2.2f
                    )
                }
            }
        }

        // Draw Glowing Cyber Vertices
        currentPoints.forEach { (point, radius) ->
            drawCircle(
                color = PrimaryCyan.copy(alpha = 0.85f),
                radius = radius,
                center = point
            )
            drawCircle(
                color = Color.White.copy(alpha = 0.95f),
                radius = radius * 0.5f,
                center = point
            )
        }
    }
}
