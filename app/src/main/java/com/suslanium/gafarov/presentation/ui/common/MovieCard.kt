package com.suslanium.gafarov.presentation.ui.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.suslanium.gafarov.R
import com.suslanium.gafarov.domain.entity.MovieSummary
import com.suslanium.gafarov.presentation.ui.theme.Black
import com.suslanium.gafarov.presentation.ui.theme.LightBlue
import com.suslanium.gafarov.presentation.ui.theme.S14_W500
import com.suslanium.gafarov.presentation.ui.theme.S16_W500
import com.suslanium.gafarov.presentation.ui.theme.SemiTransparentBlack
import com.suslanium.gafarov.presentation.ui.theme.Surface

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    shimmerOffsetProvider: () -> Float,
    movieSummary: MovieSummary,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {},
    isFavourite: Boolean = false
) {
    Card(
        modifier = modifier
            .height(93.dp)
            .fillMaxWidth()
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            ),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Surface,
            disabledContainerColor = Surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 15.dp)
        ) {
            CoilImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(40f / 63f)
                    .clip(RoundedCornerShape(5.dp)),
                imageModel = { movieSummary.thumbnailUri },
                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .shimmerEffect(
                                startOffsetXProvider = shimmerOffsetProvider
                            )
                    )
                },
                failure = {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = R.drawable.no_poster),
                        contentDescription = null
                    )
                }
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(start = 15.dp, top = 11.dp, bottom = 12.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = movieSummary.title,
                        style = S16_W500,
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Black
                    )
                    if (isFavourite) {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            imageVector = ImageVector.vectorResource(id = R.drawable.favourite),
                            contentDescription = null,
                            tint = LightBlue
                        )
                    }
                }
                Text(
                    text = "${movieSummary.genre} (${movieSummary.year})",
                    style = S14_W500,
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = SemiTransparentBlack
                )
            }
        }
    }
}