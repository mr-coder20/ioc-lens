package io.github.mrcoder20.ioclens.core.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import kotlinx.coroutines.delay

@Composable
fun TypewriterText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    typingDelayMillis: Long = 10L
) {
    var displayedText by remember(text) { mutableStateOf("") }

    LaunchedEffect(text) {
        displayedText = ""
        for (i in 1..text.length) {
            displayedText = text.substring(0, i)
            delay(typingDelayMillis)
        }
    }

    Text(
        text = displayedText,
        modifier = modifier,
        style = style,
        color = color,
        textAlign = textAlign,
        fontSize = fontSize,
        fontWeight = fontWeight,
        lineHeight = lineHeight
    )
}
