package com.suslanium.gafarov.presentation.ui.screen.details

import android.content.Context
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.suslanium.gafarov.R
import com.suslanium.gafarov.presentation.Constants
import com.suslanium.gafarov.presentation.state.DetailsState
import com.suslanium.gafarov.presentation.ui.common.ErrorContent
import com.suslanium.gafarov.presentation.ui.common.LoadingContent
import com.suslanium.gafarov.presentation.ui.screen.details.components.DetailsContent
import com.suslanium.gafarov.presentation.ui.theme.S14_W400
import com.suslanium.gafarov.presentation.ui.theme.SemiTransparentBlack

@Composable
fun DetailsScreen(
    shimmerOffsetProvider: () -> Float,
    detailsState: DetailsState,
    onRetry: () -> Unit,
    context: Context
) {

    Crossfade(targetState = detailsState, label = Constants.EMPTY_STRING) { state ->
        when (state) {
            is DetailsState.Content -> DetailsContent(
                shimmerOffsetProvider = shimmerOffsetProvider,
                movieDetails = state.content,
                context = context
            )

            DetailsState.Error -> ErrorContent(onRetry = onRetry)
            DetailsState.Loading -> LoadingContent()
            DetailsState.Empty -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(40.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.details_hint),
                    style = S14_W400,
                    textAlign = TextAlign.Center,
                    color = SemiTransparentBlack
                )
            }
        }
    }
}

