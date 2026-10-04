package io.github.mrcoder20.ioclens.core.components.slider

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import io.github.mrcoder20.ioclens.core.theme.PrimaryCyan
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

enum class PageTransitionStyle {
    CLOCK_DIAL_360,
    CAROUSEL_WHEEL_3D,
    FADE,
    PARALLAX_ZOOM,
    CUBE_3D,
    DEPTH_SLIDE,
    BOOK_FLIP_3D
}

/**
 * Replicates and elevates V2rayNG/WhiteSIM IntroSliderActivity behavior.
 * Features 360-Degree (0,0) Origin Clock Rotation Page Transitions, Symmetrical Stacked Control Deck with High Z-Index,
 * RTL Keyboard Navigation Fix, Permanent Keyboard Focus Retention, Expanded 420dp Top Glow, and Crisp Vector Arrows.
 */
@Composable
fun <T> OnboardingSlider(
    items: List<T>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    transitionStyle: PageTransitionStyle = PageTransitionStyle.CLOCK_DIAL_360,
    containerBackgroundColor: Color = Color(0xFF06080D).copy(alpha = 0.82f),
    topBarContent: @Composable (() -> Unit)? = null,
    onFinishClicked: () -> Unit,
    doneText: String = "DONE",
    pageContent: @Composable (item: T, page: Int) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val isLastPage = pagerState.currentPage == items.size - 1
    val focusRequester = remember { FocusRequester() }
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl

    val animSpec = remember { tween<Float>(durationMillis = 850, easing = FastOutSlowInEasing) }

    LaunchedEffect(pagerState.currentPage) {
        runCatching { focusRequester.requestFocus() }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(containerBackgroundColor)
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        awaitPointerEvent()
                        runCatching { focusRequester.requestFocus() }
                    }
                }
            }
            .focusRequester(focusRequester)
            .focusable()
            .onPreviewKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown) {
                    when (event.key) {
                        Key.DirectionRight, Key.PageDown -> {
                            val moveForward = !isRtl
                            if (moveForward && pagerState.currentPage < items.size - 1) {
                                coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1, animationSpec = animSpec) }
                                true
                            } else if (!moveForward && pagerState.currentPage > 0) {
                                coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1, animationSpec = animSpec) }
                                true
                            } else false
                        }
                        Key.DirectionLeft, Key.PageUp -> {
                            val moveForward = isRtl
                            if (moveForward && pagerState.currentPage < items.size - 1) {
                                coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1, animationSpec = animSpec) }
                                true
                            } else if (!moveForward && pagerState.currentPage > 0) {
                                coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1, animationSpec = animSpec) }
                                true
                            } else false
                        }
                        Key.DirectionDown -> {
                            if (pagerState.currentPage < items.size - 1) {
                                coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1, animationSpec = animSpec) }
                                true
                            } else false
                        }
                        Key.DirectionUp -> {
                            if (pagerState.currentPage > 0) {
                                coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1, animationSpec = animSpec) }
                                true
                            } else false
                        }
                        Key.Enter, Key.NumPadEnter, Key.Spacebar -> {
                            if (isLastPage) {
                                onFinishClicked()
                            } else {
                                coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1, animationSpec = animSpec) }
                            }
                            true
                        }
                        else -> false
                    }
                } else false
            }
    ) {
        // Expanded 420dp Vercel Top Mesh Glow Gradient matching Header #041C24
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(top = 2.dp)
        ) {
            // Top Slot Header
            if (topBarContent != null) {
                topBarContent()
            }

            // Pager Content with (0,0) Origin 360 Clock Rotation Transforms
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) { page ->
                val rawOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                val signOffset = if (isRtl) -rawOffset else rawOffset
                val absOffset = signOffset.absoluteValue.coerceIn(0f, 1f)

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                        .graphicsLayer {
                            when (transitionStyle) {
                                PageTransitionStyle.CLOCK_DIAL_360 -> {
                                    // Pivot anchored at (X = 0f, Y = 0f) top-left origin
                                    transformOrigin = TransformOrigin(0f, 0f)
                                    rotationZ = signOffset * 180f
                                    scaleX = (1f - absOffset * 0.25f).coerceIn(0.2f, 1f)
                                    scaleY = (1f - absOffset * 0.25f).coerceIn(0.2f, 1f)
                                    alpha = (1f - absOffset * 0.65f).coerceIn(0f, 1f)
                                }
                                PageTransitionStyle.CAROUSEL_WHEEL_3D -> {
                                    val angle = signOffset * -42f
                                    transformOrigin = TransformOrigin(0.5f, 0.5f)
                                    rotationY = angle
                                    cameraDistance = 14f * density
                                    scaleX = 0.88f + (1f - absOffset) * 0.12f
                                    scaleY = 0.88f + (1f - absOffset) * 0.12f
                                    translationX = -signOffset * size.width * 0.22f
                                    alpha = (1f - absOffset * 0.45f).coerceIn(0.15f, 1f)
                                }
                                PageTransitionStyle.FADE -> {
                                    alpha = (1f - absOffset * 1.2f).coerceIn(0f, 1f)
                                    scaleX = 0.96f + (1f - absOffset) * 0.04f
                                    scaleY = 0.96f + (1f - absOffset) * 0.04f
                                    translationX = -signOffset * size.width * 0.05f
                                }
                                PageTransitionStyle.PARALLAX_ZOOM -> {
                                    transformOrigin = TransformOrigin(0.5f, 0.5f)
                                    scaleX = 0.92f + (1f - absOffset) * 0.08f
                                    scaleY = 0.92f + (1f - absOffset) * 0.08f
                                    alpha = (1f - absOffset * 0.5f).coerceIn(0.2f, 1f)
                                    translationX = -signOffset * size.width * 0.15f
                                }
                                PageTransitionStyle.CUBE_3D -> {
                                    val pivotX = if (signOffset < 0f) 1f else 0f
                                    transformOrigin = TransformOrigin(pivotX, 0.5f)
                                    rotationY = signOffset * 60f
                                    alpha = (1f - absOffset * 0.4f).coerceIn(0.2f, 1f)
                                    cameraDistance = 12f * density
                                }
                                PageTransitionStyle.DEPTH_SLIDE -> {
                                    transformOrigin = TransformOrigin(0.5f, 0.5f)
                                    if (signOffset < 0f) {
                                        scaleX = 1f + signOffset * 0.15f
                                        scaleY = 1f + signOffset * 0.15f
                                        alpha = 1f + signOffset
                                        translationX = signOffset * size.width * 0.75f
                                    } else {
                                        scaleX = 1f
                                        scaleY = 1f
                                        alpha = 1f
                                        translationX = 0f
                                    }
                                }
                                PageTransitionStyle.BOOK_FLIP_3D -> {
                                    val pivotX = if (signOffset < 0f) 0f else 1f
                                    transformOrigin = TransformOrigin(pivotX, 0.5f)
                                    rotationY = signOffset * 30f
                                    translationX = -signOffset * size.width * 0.12f
                                    scaleX = 0.94f + (1f - absOffset) * 0.06f
                                    scaleY = 0.94f + (1f - absOffset) * 0.06f
                                    alpha = (1f - (absOffset * 0.4f)).coerceIn(0.2f, 1f)
                                    cameraDistance = 10f * density
                                }
                            }
                        }
                ) {
                    pageContent(items[page], page)
                }
            }

            // Stacked Center-Axis Symmetrical Control Deck with High Z-Index
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(horizontal = 20.dp, vertical = 6.dp)
                    .zIndex(10f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Row 1: WormDotsIndicator Locked Dead-Center at X = 50%
                WormDotsIndicator(
                    pageCount = items.size,
                    currentPage = pagerState.currentPage,
                    currentPageOffsetFraction = pagerState.currentPageOffsetFraction,
                    onDotClick = { targetPage ->
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(targetPage, animationSpec = animSpec)
                            runCatching { focusRequester.requestFocus() }
                        }
                    }
                )

                // Row 2: Centered Action Capsule Button
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    if (isLastPage) {
                        Button(
                            onClick = {
                                onFinishClicked()
                                coroutineScope.launch { runCatching { focusRequester.requestFocus() } }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF131C30).copy(alpha = 0.92f),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(50),
                            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
                            modifier = Modifier
                                .height(42.dp)
                                .border(1.2.dp, PrimaryCyan.copy(alpha = 0.65f), RoundedCornerShape(50))
                        ) {
                            Text(
                                text = doneText,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 14.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                softWrap = false
                            )
                        }
                    } else {
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1, animationSpec = animSpec)
                                    runCatching { focusRequester.requestFocus() }
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF131C30).copy(alpha = 0.92f),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(50),
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier
                                .height(42.dp)
                                .width(56.dp)
                                .border(1.2.dp, PrimaryCyan.copy(alpha = 0.65f), RoundedCornerShape(50))
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                VectorNextArrow(isRtl = isRtl)
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Crisp Vector Path Arrow Icon for Next Button.
 */
@Composable
fun VectorNextArrow(
    isRtl: Boolean,
    modifier: Modifier = Modifier,
    color: Color = Color.White
) {
    Canvas(modifier = modifier.size(20.dp)) {
        val w = size.width
        val h = size.height
        val strokeWidth = 2.2.dp.toPx()

        // Horizontal stem line
        drawLine(
            color = color,
            start = Offset(if (isRtl) w * 0.72f else w * 0.28f, h * 0.5f),
            end = Offset(if (isRtl) w * 0.28f else w * 0.72f, h * 0.5f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )

        // Arrow head path
        val path = Path().apply {
            if (isRtl) {
                moveTo(w * 0.48f, h * 0.28f)
                lineTo(w * 0.28f, h * 0.5f)
                lineTo(w * 0.48f, h * 0.72f)
            } else {
                moveTo(w * 0.52f, h * 0.28f)
                lineTo(w * 0.72f, h * 0.5f)
                lineTo(w * 0.52f, h * 0.72f)
            }
        }
        drawPath(
            path = path,
            color = color,
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )
    }
}
