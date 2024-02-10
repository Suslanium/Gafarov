package com.suslanium.gafarov.presentation.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.suslanium.gafarov.R
import com.suslanium.gafarov.presentation.ui.theme.LightBlue
import com.suslanium.gafarov.presentation.ui.theme.S14_W400
import com.suslanium.gafarov.presentation.ui.theme.S14_W500
import com.suslanium.gafarov.presentation.ui.theme.Surface

@Composable
fun ErrorContent(message: String? = null, onRetry: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp, vertical = 10.dp)
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(100.dp),
                imageVector = ImageVector.vectorResource(R.drawable.loading_error),
                contentDescription = null,
                tint = LightBlue
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = message ?: stringResource(id = R.string.loading_error_msg),
                style = S14_W400,
                textAlign = TextAlign.Center,
                color = LightBlue
            )
            Spacer(modifier = Modifier.height(36.dp))
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
}