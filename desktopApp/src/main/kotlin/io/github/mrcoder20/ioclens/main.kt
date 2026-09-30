package io.github.mrcoder20.ioclens

import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import java.io.File

fun main() = application {
    val windowState = rememberWindowState(width = 1000.dp, height = 720.dp)

    val iconPainter = remember {
        runCatching {
            val relative1 = File("docs/assets/logo/icon-512.png")
            val relative2 = File("../docs/assets/logo/icon-512.png")
            when {
                relative1.exists() -> relative1
                relative2.exists() -> relative2
                else -> null
            }
        }.getOrNull()?.let { file ->
            runCatching {
                file.inputStream().use { stream ->
                    BitmapPainter(loadImageBitmap(stream))
                }
            }.getOrNull()
        }
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "IOC Lens - Copy it. Know in a second.",
        state = windowState,
        icon = iconPainter
    ) {
        App()
    }
}
