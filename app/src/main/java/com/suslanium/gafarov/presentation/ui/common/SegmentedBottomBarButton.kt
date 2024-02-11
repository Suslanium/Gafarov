package com.suslanium.gafarov.presentation.ui.common

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonColors
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRowScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.suslanium.gafarov.presentation.ui.theme.LightBlue
import com.suslanium.gafarov.presentation.ui.theme.S14_W500
import com.suslanium.gafarov.presentation.ui.theme.Surface
import com.suslanium.gafarov.presentation.ui.theme.UltraLightBlue

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SingleChoiceSegmentedButtonRowScope.SegmentedBottomBarButton(
    text: String, selected: Boolean, onClick: () -> Unit = { }
) {
    SegmentedButton(
        modifier = Modifier
            .height(45.dp)
            .weight(1f),
        selected = selected,
        onClick = onClick,
        shape = RoundedCornerShape(
            100.dp
        ),
        icon = {},
        border = SegmentedButtonDefaults.borderStroke(Color.Transparent, 0.dp),
        colors = SegmentedButtonColors(
            activeContainerColor = LightBlue,
            activeContentColor = Surface,
            inactiveContainerColor = UltraLightBlue,
            inactiveContentColor = LightBlue,
            inactiveBorderColor = Color.Transparent,
            activeBorderColor = Color.Transparent,
            disabledActiveContainerColor = LightBlue,
            disabledActiveContentColor = Surface,
            disabledInactiveContainerColor = UltraLightBlue,
            disabledInactiveContentColor = LightBlue,
            disabledInactiveBorderColor = Color.Transparent,
            disabledActiveBorderColor = Color.Transparent
        )
    ) {
        Text(text = text, style = S14_W500, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}