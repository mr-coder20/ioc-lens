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

private class ReusablePoint(
    var x: Float = 0f,
    var y: Float = 0f,
    var radius: Float = 0f
)

/**
 * Ultra-Futuristic Cyber Polygon Mesh & Fractured Line Background for Compose Multiplatform.
 * Optimized with 100% Zero-Allocation in-place point buffers for butter-smooth 120 FPS performance.
 */
@Composable
fun CyberPolygonBackground(
    modifier: Modifier = Modifier,
    cols: Int = 4,
    rows: Int = 4
) {
    val totalNodes = cols * rows

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

    // Pre-allocate point buffer to avoid per-frame GC allocations
    val pointBuffer = remember { Array(totalNodes) { ReusablePoint() } }

    val primaryDotColor = remember { PrimaryCyan.copy(alpha = 0.8f) }
    val whiteDotColor = remember { Color.White.copy(alpha = 0.9f) }

    val cyanColors = remember { Array(101) { PrimaryCyan.copy(alpha = it / 100f * 0.55f) } }
    val tealColors = remember { Array(101) { SecondaryTeal.copy(alpha = it / 100f * 0.55f) } }
    val skyColors = remember { Array(101) { HighlightSkyBlue.copy(alpha = it / 100f * 0.55f) } }

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
        val maxDist = kotlin.math.sqrt(maxConnectDistSq)

        // Update point positions in-place
        for (idx in 0 until totalNodes) {
            val node = nodes[idx]
            val pt = pointBuffer[idx]

            val centerX = (node.cellX + 0.5f) * cellW
            val centerY = (node.cellY + 0.5f) * cellH

            pt.x = centerX + sin(time * node.speedX + node.phaseX) * (cellW * 0.35f)
            pt.y = centerY + cos(time * node.speedY + node.phaseY) * (cellH * 0.35f)
            pt.radius = node.radius
        }

        // Draw Interconnecting Cyber Lines
        for (i in 0 until totalNodes) {
            val p1 = pointBuffer[i]
            for (j in i + 1 until totalNodes) {
                val p2 = pointBuffer[j]
                val dx = p1.x - p2.x
                val dy = p1.y - p2.y
                val distSq = dx * dx + dy * dy

                if (distSq < maxConnectDistSq) {
                    val normDist = kotlin.math.sqrt(distSq)
                    val alphaIndex = ((1f - (normDist / maxDist)).coerceIn(0f, 1f) * 100).toInt()

                    val linePalette = when ((i + j) % 3) {
                        0 -> cyanColors
                        1 -> tealColors
                        else -> skyColors
                    }

                    drawLine(
                        color = linePalette[alphaIndex],
                        start = Offset(p1.x, p1.y),
                        end = Offset(p2.x, p2.y),
                        strokeWidth = 2.0f
                    )
                }
            }
        }

        // Draw Glowing Cyber Vertices
        for (idx in 0 until totalNodes) {
            val pt = pointBuffer[idx]
            val centerOffset = Offset(pt.x, pt.y)

            drawCircle(
                color = primaryDotColor,
                radius = pt.radius,
                center = centerOffset
            )
            drawCircle(
                color = whiteDotColor,
                radius = pt.radius * 0.45f,
                center = centerOffset
            )
        }
    }
}
