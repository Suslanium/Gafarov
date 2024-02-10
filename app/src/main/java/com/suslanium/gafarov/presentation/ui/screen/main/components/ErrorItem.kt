package com.suslanium.gafarov.presentation.ui.screen.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.suslanium.gafarov.R
import com.suslanium.gafarov.presentation.ui.theme.LightBlue
import com.suslanium.gafarov.presentation.ui.theme.S14_W400
import com.suslanium.gafarov.presentation.ui.theme.S14_W500
import com.suslanium.gafarov.presentation.ui.theme.Surface

@Composable
fun ErrorItem(onRetry: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.loading_error_short),
            style = S14_W400,
            textAlign = TextAlign.Start,
            color = LightBlue
        )
        Button(
            modifier = Modifier
                .width(124.dp)
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LightBlue,
                contentColor = Surface
            ),
            shape = RoundedCornerShape(100.dp),
            onClick = onRetry
        ) {
            Text(
                text = stringResource(id = R.string.retry),
                style = S14_W500,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}