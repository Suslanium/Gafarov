package com.suslanium.gafarov.presentation.ui.screen.details.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.suslanium.gafarov.R
import com.suslanium.gafarov.domain.entity.MovieDetails
import com.suslanium.gafarov.presentation.ui.common.shimmerEffect
import com.suslanium.gafarov.presentation.ui.theme.Black
import com.suslanium.gafarov.presentation.ui.theme.S14_W400
import com.suslanium.gafarov.presentation.ui.theme.S14_W500
import com.suslanium.gafarov.presentation.ui.theme.S20_W600
import com.suslanium.gafarov.presentation.ui.theme.SemiTransparentBlack
import com.suslanium.gafarov.presentation.ui.theme.Surface

@Composable
fun DetailsContent(
    shimmerOffsetProvider: () -> Float,
    movieDetails: MovieDetails,
    context: Context
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .verticalScroll(
                scrollState
            )
            .navigationBarsPadding()
    ) {
        CoilImage(
            modifier = Modifier
                .aspectRatio(360f / 533f)
                .fillMaxWidth()
                .graphicsLayer {
                    translationY = scrollState.value * 0.5f
                },
            imageModel = { movieDetails.posterUri },
            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .shimmerEffect(
                            startOffsetXProvider = shimmerOffsetProvider
                        )
                )
            }
        )
        Spacer(
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth()
                .background(Surface)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(Surface)
                .padding(horizontal = 30.dp),
            text = movieDetails.title,
            style = S20_W600,
            color = Black,
            textAlign = TextAlign.Start
        )
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .fillMaxWidth()
                .background(Surface)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(Surface)
                .padding(horizontal = 30.dp),
            text = movieDetails.description,
            style = S14_W400,
            color = SemiTransparentBlack,
            textAlign = TextAlign.Start
        )
        Spacer(
            modifier = Modifier
                .height(15.dp)
                .fillMaxWidth()
                .background(Surface)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(Surface)
                .padding(horizontal = 30.dp),
            text = buildAnnotatedString {
                withStyle(style = S14_W500.toSpanStyle()) {
                    append(context.resources.getString(R.string.genres))
                }
                withStyle(style = S14_W400.toSpanStyle()) {
                    append(movieDetails.genres.joinToString(separator = ", "))
                }
            },
            color = SemiTransparentBlack,
            textAlign = TextAlign.Start
        )
        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
                .background(Surface)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(Surface)
                .padding(horizontal = 30.dp),
            text = buildAnnotatedString {
                withStyle(style = S14_W500.toSpanStyle()) {
                    append(context.resources.getString(R.string.countries))
                }
                withStyle(style = S14_W400.toSpanStyle()) {
                    append(movieDetails.countries.joinToString(separator = ", "))
                }
            },
            color = SemiTransparentBlack,
            textAlign = TextAlign.Start
        )
        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
                .background(Surface)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(Surface)
                .padding(horizontal = 30.dp),
            text = buildAnnotatedString {
                withStyle(style = S14_W500.toSpanStyle()) {
                    append(context.resources.getString(R.string.year))
                }
                withStyle(style = S14_W400.toSpanStyle()) {
                    append(movieDetails.year.toString())
                }
            },
            color = SemiTransparentBlack,
            textAlign = TextAlign.Start
        )
        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
                .background(Surface)
        )
    }
}