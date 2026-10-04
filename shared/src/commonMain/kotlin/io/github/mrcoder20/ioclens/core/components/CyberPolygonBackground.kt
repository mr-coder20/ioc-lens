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
 * Optimized with grid spatial distribution for 120 FPS mobile performance with zero lag.
 */
@Composable
fun CyberPolygonBackground(
    modifier: Modifier = Modifier,
    cols: Int = 4,
    rows: Int = 4
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
                        speedX = 0.35f + random.nextFloat() * 0.35f,
                        speedY = 0.35f + random.nextFloat() * 0.35f,
                        radius = 2.2f + random.nextFloat() * 1.8f
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
        val maxConnectDistSq = (cellW.coerceAtLeast(cellH) * 1.5f).let { it * it }

        val currentPoints = nodes.map { node ->
            val centerX = (node.cellX + 0.5f) * cellW
            val centerY = (node.cellY + 0.5f) * cellH

            val offsetX = sin(time * node.speedX + node.phaseX) * (cellW * 0.35f)
            val offsetY = cos(time * node.speedY + node.phaseY) * (cellH * 0.35f)

            Offset(centerX + offsetX, centerY + offsetY) to node.radius
        }

        // Draw Interconnecting Cyber Lines with Squared Distance Checks (Zero Sqrt Overhead)
        val totalPoints = currentPoints.size
        for (i in 0 until totalPoints) {
            val (p1, _) = currentPoints[i]
            for (j in i + 1 until totalPoints) {
                val (p2, _) = currentPoints[j]
                val dx = p1.x - p2.x
                val dy = p1.y - p2.y
                val distSq = dx * dx + dy * dy

                if (distSq < maxConnectDistSq) {
                    val normDist = kotlin.math.sqrt(distSq)
                    val maxDist = kotlin.math.sqrt(maxConnectDistSq)
                    val lineAlpha = (1f - (normDist / maxDist)) * 0.55f
                    val lineColor = when ((i + j) % 3) {
                        0 -> PrimaryCyan
                        1 -> SecondaryTeal
                        else -> HighlightSkyBlue
                    }

                    drawLine(
                        color = lineColor.copy(alpha = lineAlpha),
                        start = p1,
                        end = p2,
                        strokeWidth = 2.0f
                    )
                }
            }
        }

        // Draw Glowing Cyber Vertices
        currentPoints.forEach { (point, radius) ->
            drawCircle(
                color = PrimaryCyan.copy(alpha = 0.8f),
                radius = radius,
                center = point
            )
            drawCircle(
                color = Color.White.copy(alpha = 0.9f),
                radius = radius * 0.45f,
                center = point
            )
        }
    }
}
