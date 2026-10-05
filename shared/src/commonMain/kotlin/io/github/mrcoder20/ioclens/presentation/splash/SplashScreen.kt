package io.github.mrcoder20.ioclens.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.mrcoder20.ioclens.core.components.CyberPolygonBackground
import io.github.mrcoder20.ioclens.core.components.LiquidmorphicBackground
import io.github.mrcoder20.ioclens.core.components.LottiePreloader
import io.github.mrcoder20.ioclens.core.components.SlideAnimatedGraphic
import io.github.mrcoder20.ioclens.core.localization.AppLanguage
import io.github.mrcoder20.ioclens.core.theme.PrimaryCyan
import io.github.mrcoder20.ioclens.presentation.intro.IntroSlideData
import ioclens.shared.generated.resources.Res
import ioclens.shared.generated.resources.app_logo_mark
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import kotlin.time.Duration.Companion.milliseconds

/**
 * Ultra-Minimalist 5-Second Cyber Splash Screen with Static App Logo & Ultra-Sleek Progress Line.
 * Pre-renders all 4 intro slides and Lottie JSONs in background for 0ms lag-free 120 FPS slider execution.
 */
@Composable
fun SplashScreen(
    appLanguage: AppLanguage,
    onSplashFinished: () -> Unit,
    modifier: Modifier = Modifier
) {
    var progress by remember { mutableFloatStateOf(0f) }

    // 5-second timer and background preload task
    LaunchedEffect(Unit) {
        // Step 1: Preload Lottie JSON animations into RAM cache
        launch {
            LottiePreloader.preloadAll()
        }

        // Step 2: Smooth 3.3s Progress Bar (100 ticks x 33ms = 3300ms)
        val totalDurationMs = 2000L
        val stepIntervalMs = 20L
        val totalSteps = totalDurationMs / stepIntervalMs

        for (step in 1..totalSteps) {
            delay(stepIntervalMs.milliseconds)
            progress = (step.toFloat() / totalSteps.toFloat()).coerceIn(0f, 1f)
        }

        delay(20.milliseconds)
        onSplashFinished()
    }

    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val isCompact = maxHeight < 580.dp
        val logoSize = if (isCompact) 100.dp else 150.dp

        // 1. Liquidmorphism Animated Background Blobs
        LiquidmorphicBackground(
            modifier = Modifier.fillMaxSize()
        )

        // 2. Fractured Cyber Polygon Mesh Background
        CyberPolygonBackground(
            modifier = Modifier.fillMaxSize()
        )

        // 3. Vercel Top Mesh Glow Gradient
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .height(420.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF041C24),
                            PrimaryCyan.copy(alpha = 0.22f),
                            PrimaryCyan.copy(alpha = 0.08f),
                            Color.Transparent
                        )
                    )
                )
        )

        // 4. Hidden Offscreen Pre-renderer for 4 Intro Slides
        Box(
            modifier = Modifier
                .graphicsLayer {
                    alpha = 0.001f
                }
                .size(1.dp)
        ) {
            val slides = IntroSlideData.getSlides(appLanguage)
            slides.forEach { slide ->
                SlideAnimatedGraphic(
                    slideId = slide.id,
                    emoji = slide.iconEmoji,
                    size = 100.dp
                )
            }
        }

        // 5. Ultra-Minimalist Main Splash Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = if (isCompact) 16.dp else 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Static App Logo Mark Centerpiece (No animation, clean backdrop ambient glow)
            Box(
                modifier = Modifier.size(logoSize),
                contentAlignment = Alignment.Center
            ) {
                // Background Ambient Cyber Glow
                Box(
                    modifier = Modifier
                        .size(logoSize * 0.75f)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    PrimaryCyan.copy(alpha = 0.35f),
                                    Color.Transparent
                                )
                            )
                        )
                )

                Image(
                    painter = painterResource(Res.drawable.app_logo_mark),
                    contentDescription = "IOC Lens App Icon",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(if (isCompact) 20.dp else 32.dp))

            // Premium Modern App Title Typography ("IOC LENS")
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "IOC ",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = if (isCompact) 28.sp else 38.sp,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "LENS",
                    style = MaterialTheme.typography.headlineLarge,
                    color = PrimaryCyan,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = if (isCompact) 28.sp else 38.sp,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(if (isCompact) 48.dp else 72.dp))

            // Ultra-Minimalist Glowing Cyber Progress Line & Percentage
            Column(
                modifier = Modifier.width(if (isCompact) 200.dp else 240.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp)
                        .clip(RoundedCornerShape(2.dp)),
                    color = PrimaryCyan,
                    trackColor = Color(0xFF131C30).copy(alpha = 0.6f)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "${(progress * 100).toInt()}%",
                    style = MaterialTheme.typography.labelSmall,
                    fontFamily = FontFamily.Monospace,
                    color = PrimaryCyan.copy(alpha = 0.85f),
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                )
            }
        }
    }
}
