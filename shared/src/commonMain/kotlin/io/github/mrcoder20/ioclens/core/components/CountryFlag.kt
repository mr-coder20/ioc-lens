package io.github.mrcoder20.ioclens.core.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.mrcoder20.ioclens.core.localization.AppLanguage

@Composable
fun CountryFlag(
    language: AppLanguage,
    modifier: Modifier = Modifier,
    width: Dp = 24.dp,
    height: Dp = 16.dp
) {
    val cornerShape = RoundedCornerShape(3.dp)

    Canvas(
        modifier = modifier
            .width(width)
            .height(height)
            .clip(cornerShape)
            .border(0.5.dp, Color.White.copy(alpha = 0.3f), cornerShape)
    ) {
        val w = size.width
        val h = size.height

        when (language) {
            AppLanguage.PERSIAN -> {
                // Iran Flag: Green, White, Red stripes + emblem
                val stripeH = h / 3f
                drawRect(color = Color(0xFF239F40), topLeft = Offset(0f, 0f), size = Size(w, stripeH))
                drawRect(color = Color(0xFFFFFFFF), topLeft = Offset(0f, stripeH), size = Size(w, stripeH))
                drawRect(color = Color(0xFFDA0000), topLeft = Offset(0f, stripeH * 2f), size = Size(w, stripeH))

                // Center Emblem symbol (Red)
                drawCircle(color = Color(0xFFDA0000), radius = stripeH * 0.35f, center = Offset(w / 2f, h / 2f))
            }
            AppLanguage.RUSSIAN -> {
                // Russia Flag: White, Blue, Red stripes
                val stripeH = h / 3f
                drawRect(color = Color(0xFFFFFFFF), topLeft = Offset(0f, 0f), size = Size(w, stripeH))
                drawRect(color = Color(0xFF0039A6), topLeft = Offset(0f, stripeH), size = Size(w, stripeH))
                drawRect(color = Color(0xFFD52B1E), topLeft = Offset(0f, stripeH * 2f), size = Size(w, stripeH))
            }
            AppLanguage.ENGLISH -> {
                // UK Flag (Union Jack style)
                drawRect(color = Color(0xFF012169), size = size) // Dark Blue background

                val strokeW = h * 0.22f
                val redStrokeW = strokeW * 0.5f

                // Diagonal White Cross
                drawLine(color = Color.White, start = Offset(0f, 0f), end = Offset(w, h), strokeWidth = strokeW)
                drawLine(color = Color.White, start = Offset(w, 0f), end = Offset(0f, h), strokeWidth = strokeW)

                // Diagonal Red Cross
                drawLine(color = Color(0xFFC8102E), start = Offset(0f, 0f), end = Offset(w, h), strokeWidth = redStrokeW)
                drawLine(color = Color(0xFFC8102E), start = Offset(w, 0f), end = Offset(0f, h), strokeWidth = redStrokeW)

                // St George's White Cross
                val vertWhiteW = w * 0.28f
                val horizWhiteH = h * 0.28f
                drawRect(color = Color.White, topLeft = Offset((w - vertWhiteW) / 2f, 0f), size = Size(vertWhiteW, h))
                drawRect(color = Color.White, topLeft = Offset(0f, (h - horizWhiteH) / 2f), size = Size(w, horizWhiteH))

                // St George's Red Cross
                val vertRedW = vertWhiteW * 0.6f
                val horizRedH = horizWhiteH * 0.6f
                drawRect(color = Color(0xFFC8102E), topLeft = Offset((w - vertRedW) / 2f, 0f), size = Size(vertRedW, h))
                drawRect(color = Color(0xFFC8102E), topLeft = Offset(0f, (h - horizRedH) / 2f), size = Size(w, horizRedH))
            }
        }
    }
}
