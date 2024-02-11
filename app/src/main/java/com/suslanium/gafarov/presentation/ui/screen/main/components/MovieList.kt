package com.suslanium.gafarov.presentation.ui.screen.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.suslanium.gafarov.domain.entity.MovieSummary
import com.suslanium.gafarov.presentation.ui.common.MovieCard
import com.suslanium.gafarov.presentation.ui.theme.LightBlue

@Composable
fun MovieList(
    shimmerOffsetProvider: () -> Float,
    lazyListStateProvider: () -> LazyListState,
    moviePagingItems: LazyPagingItems<MovieSummary>,
    onMovieClick: (Long) -> Unit,
    onLongMovieClick: (MovieSummary) -> Unit,
    favouriteMovieIdsProvider: () -> HashSet<Long>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 90.dp),
        state = lazyListStateProvider()
    ) {
        items(
            moviePagingItems.itemCount,
            key = { moviePagingItems[it]?.listUUID ?: it }) {
            val movie = moviePagingItems[it]
            if (movie != null) {
                MovieCard(
                    shimmerOffsetProvider = shimmerOffsetProvider,
                    movieSummary = movie,
                    onClick = {
                        onMovieClick(movie.id)
                    },
                    onLongClick = {
                        onLongMovieClick(movie)
                    },
                    isFavourite = favouriteMovieIdsProvider().contains(movie.id)
                )
            }
        }

        when (moviePagingItems.loadState.append) {
            is LoadState.Error -> item {
                ErrorItem(moviePagingItems::retry)
            }

            LoadState.Loading -> item {
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(26.dp),
                        color = LightBlue,
                        strokeWidth = 5.dp
                    )
                }
            }

            is LoadState.NotLoading -> Unit
        }
    }
}