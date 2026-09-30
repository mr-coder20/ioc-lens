package io.github.mrcoder20.ioclens

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "IOCLens",
    ) {
        App()
    }
}