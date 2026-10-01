package io.github.mrcoder20.ioclens.presentation.intro

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.mrcoder20.ioclens.core.components.AnimatedSnowBackground
import io.github.mrcoder20.ioclens.core.components.CountryFlag
import io.github.mrcoder20.ioclens.core.components.PageIndicator
import io.github.mrcoder20.ioclens.core.components.SlideAnimatedGraphic
import io.github.mrcoder20.ioclens.core.components.TypewriterText
import io.github.mrcoder20.ioclens.core.localization.AppLanguage
import io.github.mrcoder20.ioclens.core.localization.LocalizationProvider
import io.github.mrcoder20.ioclens.domain.model.IntroSlide
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun IntroScreen(
    viewModel: IntroViewModel,
    onIntroFinished: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val strings = LocalizationProvider.getStrings(uiState.currentLanguage)
    val pagerState = rememberPagerState(pageCount = { uiState.slides.size })
    val coroutineScope = rememberCoroutineScope()
    var isLanguageMenuExpanded by remember { mutableStateOf(false) }

    val isDark = isSystemInDarkTheme()

    LaunchedEffect(pagerState.currentPage) {
        viewModel.onEvent(IntroEvent.PageChanged(pagerState.currentPage))
    }

    LaunchedEffect(uiState.isCompleted) {
        if (uiState.isCompleted) {
            onIntroFinished()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Ambient Cyber Background Glow
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .height(320.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.22f),
                            MaterialTheme.colorScheme.background
                        )
                    )
                )
        )

        // Animated Falling Cyber Particles / Snow Background
        AnimatedSnowBackground(
            isDark = isDark,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(top = 8.dp) // Neat compact top clearance
        ) {
            // Top Bar with App Title & Language Selector (Vector Flags)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "IOC Lens",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )

                // Clean Vector Flag Language Selector Button
                Box {
                    Surface(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .clickable { isLanguageMenuExpanded = true },
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.85f),
                        shape = RoundedCornerShape(50),
                        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.35f))
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            CountryFlag(language = uiState.currentLanguage)
                            Text(
                                text = uiState.currentLanguage.displayName,
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
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

            // Full-Bleed Edge-to-Edge Pager Content with 3D Book Page-Flip Transition
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) { page ->
                val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                val absOffset = pageOffset.absoluteValue.coerceIn(0f, 1f)

                IntroSlideCard(
                    slide = uiState.slides[page],
                    modifier = Modifier
                        .padding(horizontal = 2.dp, vertical = 4.dp)
                        .graphicsLayer {
                            // 3D Book Page-Flip, Scale, and Fade Effect
                            alpha = 1f - (absOffset * 0.6f)
                            scaleX = 0.92f + (1f - absOffset) * 0.08f
                            scaleY = 0.92f + (1f - absOffset) * 0.08f
                            rotationY = pageOffset * -16f
                            cameraDistance = 8f * density
                        }
                )
            }

            // Perfectly Balanced Symmetrical Bottom Navigation Bar with Navigation Bar Clearance
            // Left: Back/Previous | Center: All 4 Locked Page Indicators | Right: Next/Start
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding() // Clear bottom 3-button/gesture bar!
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Left Slot: Compact Previous Button (or placeholder)
                Box(
                    modifier = Modifier.width(85.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (pagerState.currentPage > 0) {
                        OutlinedButton(
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                }
                            },
                            shape = RoundedCornerShape(50),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
                            modifier = Modifier.height(40.dp)
                        ) {
                            Text(
                                text = strings.previous,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp,
                                maxLines = 1,
                                softWrap = false
                            )
                        }
                    }
                }

                // Center Slot: All 4 Page Indicators locked in dead center
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    PageIndicator(
                        pageCount = uiState.slides.size,
                        currentPage = pagerState.currentPage,
                        onDotClick = { targetPage ->
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(targetPage)
                            }
                        },
                        activeColor = MaterialTheme.colorScheme.primary,
                        inactiveColor = MaterialTheme.colorScheme.outline
                    )
                }

                // Right Slot: Spacious Next / Start Button Slot (Fits "Get Started 🚀" 100% without clipping)
                Box(
                    modifier = Modifier.width(110.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    val isLastPage = pagerState.currentPage == uiState.slides.size - 1
                    Button(
                        onClick = {
                            if (isLastPage) {
                                viewModel.onEvent(IntroEvent.CompleteClicked)
                            } else {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        shape = RoundedCornerShape(50),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier.height(40.dp)
                    ) {
                        Text(
                            text = if (isLastPage) strings.start else strings.next,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            maxLines = 1,
                            softWrap = false
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun IntroSlideCard(
    slide: IntroSlide,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxSize(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f)
        ),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize()
        ) {
            val isCompact = maxHeight < 480.dp

            val iconSize = if (isCompact) 60.dp else 84.dp
            val titleSize = if (isCompact) 17.sp else 21.sp
            val bodySize = if (isCompact) 12.sp else 14.sp
            val spacerH = if (isCompact) 12.dp else 20.dp

            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(if (isCompact) 16.dp else 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Vector Animated Graphic
                SlideAnimatedGraphic(
                    slideId = slide.id,
                    emoji = slide.iconEmoji,
                    size = iconSize,
                    emojiFontSize = if (isCompact) 26 else 36
                )

                Spacer(modifier = Modifier.height(spacerH))

                // Badge Tag
                Surface(
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(50),
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.35f))
                ) {
                    Text(
                        text = slide.badgeText,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 5.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = if (isCompact) 11.sp else 13.sp
                    )
                }

                Spacer(modifier = Modifier.height(spacerH))

                // Title (Static display - no animation)
                Text(
                    text = slide.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = titleSize
                )

                if (slide.subtitle.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = slide.subtitle,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(spacerH))

                // Description with High-Speed Typewriter Animation Exclusively
                TypewriterText(
                    text = slide.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    fontSize = bodySize,
                    lineHeight = if (isCompact) 18.sp else 22.sp,
                    typingDelayMillis = 8L
                )
            }
        }
    }
}
