package io.github.mrcoder20.ioclens.core.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PageIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    onDotClick: ((Int) -> Unit)? = null,
    activeColor: Color = MaterialTheme.colorScheme.primary,
    inactiveColor: Color = MaterialTheme.colorScheme.outline,
    indicatorHeight: Dp = 6.dp,
    activeIndicatorWidth: Dp = 18.dp,
    inactiveIndicatorWidth: Dp = 6.dp,
    spacing: Dp = 5.dp
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until pageCount) {
            val isSelected = i == currentPage
            val animatedWidth by animateDpAsState(
                targetValue = if (isSelected) activeIndicatorWidth else inactiveIndicatorWidth,
                animationSpec = tween(durationMillis = 300)
            )

            Box(
                modifier = Modifier
                    .height(indicatorHeight)
                    .width(animatedWidth)
                    .clip(CircleShape)
                    .background(if (isSelected) activeColor else inactiveColor)
                    .then(
                        if (onDotClick != null) {
                            Modifier.clickable { onDotClick(i) }
                        } else Modifier
                    )
            )
        }
    }
}
