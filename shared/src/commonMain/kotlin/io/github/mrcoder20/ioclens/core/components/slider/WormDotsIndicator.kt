package io.github.mrcoder20.ioclens.core.components.slider

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.mrcoder20.ioclens.core.theme.PrimaryCyan
import io.github.mrcoder20.ioclens.core.theme.PrimaryCyanVariant
import kotlin.math.floor

/**
 * Replicates and elevates tbuonomo's WormDotsIndicator from OmneyaOsman/OnBoardingScreen.
 * Draws precision stroke rings for inactive dots and a real-time elastic worm dot
 * that stretches continuously between dots during page swipes.
 */
@Composable
fun WormDotsIndicator(
    pageCount: Int,
    currentPage: Int,
    currentPageOffsetFraction: Float,
    onDotClick: ((Int) -> Unit)? = null,
    modifier: Modifier = Modifier,
    dotSize: Dp = 10.dp,
    spacing: Dp = 10.dp,
    activeColor: Color = PrimaryCyan,
    strokeColor: Color = Color(0xFF334155)
) {
    val totalWidth = (dotSize * pageCount) + (spacing * (pageCount - 1))

    Box(
        modifier = modifier
            .width(totalWidth)
            .height(dotSize + 8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        // Draw Precision Stroke Rings for all inactive dots
        repeat(pageCount) { index ->
            val xOffset = (dotSize + spacing) * index
            Box(
                modifier = Modifier
                    .offset(x = xOffset)
                    .size(dotSize)
                    .clip(CircleShape)
                    .border(1.2.dp, strokeColor, CircleShape)
                    .then(
                        if (onDotClick != null) {
                            Modifier.clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                onDotClick(index)
                            }
                        } else {
                            Modifier
                        }
                    )
            )
        }

        // Real-Time Elastic Worm Calculation
        val rawProgress = (currentPage + currentPageOffsetFraction).coerceIn(0f, (pageCount - 1).toFloat())
        val leftIndex = floor(rawProgress).toInt().coerceIn(0, pageCount - 1)
        val fraction = rawProgress - leftIndex

        val stepPx = dotSize + spacing

        val wormStartDp: Dp
        val wormEndDp: Dp

        if (fraction <= 0.5f) {
            val headProgress = fraction * 2f
            wormStartDp = stepPx * leftIndex
            wormEndDp = stepPx * leftIndex + dotSize + (stepPx * headProgress)
        } else {
            val tailProgress = (fraction - 0.5f) * 2f
            wormStartDp = stepPx * leftIndex + (stepPx * tailProgress)
            wormEndDp = stepPx * (leftIndex + 1) + dotSize
        }

        val wormWidthDp = (wormEndDp - wormStartDp).coerceAtLeast(dotSize)

        // Outer Halo Aura
        Box(
            modifier = Modifier
                .offset(x = wormStartDp - 3.dp)
                .width(wormWidthDp + 6.dp)
                .height(dotSize + 6.dp)
                .clip(RoundedCornerShape(50))
                .background(activeColor.copy(alpha = 0.22f))
        )

        // Elastic Worm Body
        Box(
            modifier = Modifier
                .offset(x = wormStartDp)
                .width(wormWidthDp)
                .height(dotSize)
                .clip(RoundedCornerShape(50))
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(activeColor, PrimaryCyanVariant)
                    )
                )
                .border(0.8.dp, Color.White.copy(alpha = 0.6f), RoundedCornerShape(50)),
            contentAlignment = Alignment.Center
        ) {
            // Fantasy Micro-Glow Core Dot
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.9f))
            )
        }
    }
}
