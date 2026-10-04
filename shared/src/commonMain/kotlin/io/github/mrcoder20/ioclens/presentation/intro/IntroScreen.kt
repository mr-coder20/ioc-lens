package io.github.mrcoder20.ioclens.presentation.intro

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.mrcoder20.ioclens.core.components.CountryFlag
import io.github.mrcoder20.ioclens.core.components.CyberPolygonBackground
import io.github.mrcoder20.ioclens.core.components.LiquidmorphicBackground
import io.github.mrcoder20.ioclens.core.components.SlideAnimatedGraphic
import io.github.mrcoder20.ioclens.core.components.TypewriterText
import io.github.mrcoder20.ioclens.core.components.slider.OnboardingSlider
import io.github.mrcoder20.ioclens.core.components.slider.PageTransitionStyle
import io.github.mrcoder20.ioclens.core.localization.AppLanguage
import io.github.mrcoder20.ioclens.core.localization.LocalizationProvider
import io.github.mrcoder20.ioclens.core.theme.PrimaryCyan
import io.github.mrcoder20.ioclens.domain.model.IntroSlide

/**
 * Ultra-Sleek Translucent Glassmorphism Intro Screen with 360 Clock Dial Radial Rotation.
 */
@Composable
fun IntroScreen(
    viewModel: IntroViewModel,
    onIntroFinished: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val strings = LocalizationProvider.getStrings(uiState.currentLanguage)
    val pagerState = rememberPagerState(pageCount = { uiState.slides.size })
    var isLanguageMenuExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(pagerState.currentPage) {
        viewModel.onEvent(IntroEvent.PageChanged(pagerState.currentPage))
    }

    LaunchedEffect(uiState.isCompleted) {
        if (uiState.isCompleted) {
            onIntroFinished()
        }
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // 1. Liquidmorphism Animated Background Blobs
        LiquidmorphicBackground(
            modifier = Modifier.fillMaxSize()
        )

        // 2. Fractured Cyber Polygon Mesh Background
        CyberPolygonBackground(
            modifier = Modifier.fillMaxSize()
        )

        // 3. Onboarding Slider Container with 360 Clock Dial Radial Rotation Transition
        OnboardingSlider(
            items = uiState.slides,
            pagerState = pagerState,
            transitionStyle = PageTransitionStyle.CLOCK_DIAL_360,
            doneText = "DONE",
            onFinishClicked = {
                viewModel.onEvent(IntroEvent.CompleteClicked)
            },
            topBarContent = {
                // Raycast Command Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Title Badge (Clean - without v1.0.0 text)
                    Surface(
                        color = PrimaryCyan.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(6.dp),
                        border = androidx.compose.foundation.BorderStroke(0.8.dp, PrimaryCyan.copy(alpha = 0.35f))
                    ) {
                        Text(
                            text = "IOC LENS",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            style = MaterialTheme.typography.labelSmall,
                            fontFamily = FontFamily.Monospace,
                            color = PrimaryCyan,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp
                        )
                    }

                    // Country Flag Language Selector
                    Box {
                        Surface(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { isLanguageMenuExpanded = true },
                            color = Color(0xFF0E1424).copy(alpha = 0.85f),
                            shape = RoundedCornerShape(8.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.12f))
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                CountryFlag(language = uiState.currentLanguage)
                                Text(
                                    text = uiState.currentLanguage.displayName,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color(0xFFF9FAFB),
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        DropdownMenu(
                            expanded = isLanguageMenuExpanded,
                            onDismissRequest = { isLanguageMenuExpanded = false }
                        ) {
                            AppLanguage.entries.forEach { lang ->
                                DropdownMenuItem(
                                    text = {
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            CountryFlag(language = lang)
                                            Text(
                                                text = lang.displayName,
                                                fontWeight = if (lang == uiState.currentLanguage) FontWeight.Bold else FontWeight.Normal
                                            )
                                        }
                                    },
                                    onClick = {
                                        viewModel.onEvent(IntroEvent.LanguageChanged(lang))
                                        isLanguageMenuExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
        ) { slide, _ ->
            IntroSlideCard(slide = slide)
        }
    }
}

@Composable
private fun IntroSlideCard(
    slide: IntroSlide,
    modifier: Modifier = Modifier
) {
    // Highly Translucent Glassmorphic Card (38% opacity for crystal clear background visibility)
    Card(
        modifier = modifier.fillMaxSize(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF0C1322).copy(alpha = 0.38f)
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.2.dp,
            Brush.linearGradient(
                colors = listOf(
                    Color.White.copy(alpha = 0.28f),
                    PrimaryCyan.copy(alpha = 0.45f),
                    Color.Black.copy(alpha = 0.5f)
                )
            )
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize()
        ) {
            val isCompact = maxHeight < 520.dp
            val scrollState = rememberScrollState()
            val graphicSize = if (isCompact) 110.dp else 260.dp

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(horizontal = 20.dp, vertical = if (isCompact) 6.dp else 14.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Top Graphic (Proportioned for zero mobile scrolling & large desktop view)
                Box(
                    modifier = Modifier
                        .size(graphicSize)
                        .aspectRatio(1f),
                    contentAlignment = Alignment.Center
                ) {
                    SlideAnimatedGraphic(
                        slideId = slide.id,
                        emoji = slide.iconEmoji,
                        size = graphicSize,
                        emojiFontSize = if (isCompact) 28 else 44
                    )
                }

                Spacer(modifier = Modifier.height(if (isCompact) 6.dp else 12.dp))

                // Status Badge Pill
                Surface(
                    color = Color(0xFF16233B).copy(alpha = 0.85f),
                    shape = RoundedCornerShape(50),
                    border = androidx.compose.foundation.BorderStroke(1.dp, PrimaryCyan.copy(alpha = 0.35f))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(5.dp)
                                .clip(CircleShape)
                                .background(PrimaryCyan)
                        )
                        Text(
                            text = slide.badgeText,
                            color = PrimaryCyan,
                            style = MaterialTheme.typography.labelSmall,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = if (isCompact) 10.sp else 11.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(if (isCompact) 6.dp else 12.dp))

                // Responsive Title
                Text(
                    text = slide.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFFF9FAFB),
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center,
                    fontSize = if (isCompact) 17.sp else 22.sp,
                    lineHeight = if (isCompact) 22.sp else 28.sp,
                    letterSpacing = (-0.02).sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                if (slide.subtitle.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = slide.subtitle,
                        style = MaterialTheme.typography.titleMedium,
                        color = PrimaryCyan,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(if (isCompact) 6.dp else 10.dp))

                // Responsive Typewriter Description Text
                TypewriterText(
                    text = slide.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF9CA3AF),
                    textAlign = TextAlign.Center,
                    fontSize = if (isCompact) 12.sp else 14.sp,
                    lineHeight = if (isCompact) 17.sp else 22.sp,
                    typingDelayMillis = 8L
                )
            }
        }
    }
}
