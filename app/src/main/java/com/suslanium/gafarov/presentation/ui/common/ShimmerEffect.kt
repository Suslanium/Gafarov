package com.suslanium.gafarov.presentation.ui.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import com.suslanium.gafarov.presentation.ui.theme.Gray
import com.suslanium.gafarov.presentation.ui.theme.LightGray

fun Modifier.shimmerEffect(
    startOffsetXProvider: () -> Float, backgroundColor: Color = LightGray, shimmerColor: Color = Gray
): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    drawBehind {
        drawRect(
            brush = Brush.linearGradient(
                colors = listOf(
                    backgroundColor, shimmerColor, backgroundColor
                ), start = Offset(startOffsetXProvider() * size.width.toFloat(), 0f), end = Offset(
                    startOffsetXProvider() * size.width.toFloat() + size.width.toFloat(), size.height.toFloat()
                )
            )
        )
    }.onGloballyPositioned {
        size = it.size
    }
}