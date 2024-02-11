package com.suslanium.gafarov.presentation.ui.screen.favourites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.suslanium.gafarov.R
import com.suslanium.gafarov.domain.entity.MovieSummary
import com.suslanium.gafarov.presentation.ui.common.MovieCard
import com.suslanium.gafarov.presentation.ui.theme.S14_W400
import com.suslanium.gafarov.presentation.ui.theme.SemiTransparentBlack

@Composable
fun FavouritesScreen(
    paddingValuesProvider: () -> PaddingValues,
    movieListProvider: () -> List<MovieSummary>,
    shimmerOffsetProvider: () -> Float,
    lazyListStateProvider: () -> LazyListState,
    onMovieClick: (Long) -> Unit,
    onLongMovieClick: (MovieSummary) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(PaddingValues(top = paddingValuesProvider().calculateTopPadding()))
            .navigationBarsPadding()
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 90.dp),
        state = lazyListStateProvider()
    ) {
        if (movieListProvider().isEmpty()) {
            item {
                //Hint item
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.favourites_hint),
                        style = S14_W400,
                        textAlign = TextAlign.Center,
                        color = SemiTransparentBlack
                    )
                }
            }
        }

        items(
            movieListProvider().count(),
            key = { movieListProvider()[it].listUUID }) {
            val movie = movieListProvider()[it]
            MovieCard(
                shimmerOffsetProvider = shimmerOffsetProvider,
                movieSummary = movie,
                onClick = {
                    onMovieClick(movie.id)
                },
                onLongClick = {
                    onLongMovieClick(movie)
                },
                isFavourite = true
            )
        }
    }
}