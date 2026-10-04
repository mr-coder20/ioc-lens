package io.github.mrcoder20.ioclens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.decodeToImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import io.github.mrcoder20.ioclens.core.theme.IOCLensTheme
import io.github.mrcoder20.ioclens.core.theme.PrimaryCyan
import java.io.File
import java.io.InputStream

fun main() = application {
    val windowState = rememberWindowState(width = 1040.dp, height = 740.dp)

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
                    BitmapPainter(stream.readAllBytes().decodeToImageBitmap())
                }
            }.getOrNull()
        }
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "IOC Lens - Copy it. Know in a second.",
        state = windowState,
        icon = iconPainter,
        undecorated = true
    ) {
        IOCLensTheme {
            Column(modifier = Modifier.fillMaxSize().background(Color(0xFF041C24))) {
                // Custom Dead-Centered Window Title Bar
                WindowDraggableArea {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(38.dp)
                            .background(Color(0xFF041C24))
                            .padding(horizontal = 14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Left Slot: Empty spacing for 100% dead-center balance
                        Box(modifier = Modifier.width(90.dp))

                        // Center Slot: 100% DEAD-CENTERED Title Text
                        Text(
                            text = "IOC Lens - Copy it. Know in a second.",
                            color = PrimaryCyan,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            fontFamily = FontFamily.Monospace,
                            textAlign = TextAlign.Center
                        )

                        // Right Slot: Glassmorphic Window Controls (Minimize, Maximize, Close)
                        Row(
                            modifier = Modifier.width(90.dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                // Minimize button (-)
                                Surface(
                                    modifier = Modifier
                                        .size(22.dp)
                                        .clip(CircleShape)
                                        .clickable { windowState.isMinimized = true },
                                    color = Color.White.copy(alpha = 0.12f)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(text = "─", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                    }
                                }

                                // Maximize / Restore button (□)
                                Surface(
                                    modifier = Modifier
                                        .size(22.dp)
                                        .clip(CircleShape)
                                        .clickable {
                                            windowState.placement = if (windowState.placement == WindowPlacement.Maximized) {
                                                WindowPlacement.Floating
                                            } else {
                                                WindowPlacement.Maximized
                                            }
                                        },
                                    color = Color.White.copy(alpha = 0.12f)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(text = "□", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                    }
                                }

                                // Close button (✕)
                                Surface(
                                    modifier = Modifier
                                        .size(22.dp)
                                        .clip(CircleShape)
                                        .clickable { exitApplication() },
                                    color = Color(0xFFEF4444).copy(alpha = 0.85f)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(text = "✕", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                    }
                }

                // Main Application Content
                Box(modifier = Modifier.weight(1f)) {
                    App()
                }
            }
        }
    }
}
