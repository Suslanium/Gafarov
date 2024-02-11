package com.suslanium.gafarov.presentation.ui.screen.main

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.suslanium.gafarov.domain.entity.MovieSummary
import com.suslanium.gafarov.presentation.Constants
import com.suslanium.gafarov.presentation.ui.common.ErrorContent
import com.suslanium.gafarov.presentation.ui.common.LoadingContent
import com.suslanium.gafarov.presentation.ui.screen.main.components.MovieList

@Composable
fun MainScreen(
    paddingValuesProvider: () -> PaddingValues,
    moviePagingItemsProvider: () -> LazyPagingItems<MovieSummary>,
    shimmerOffsetProvider: () -> Float,
    lazyListStateProvider: () -> LazyListState,
    onMovieClick: (Long) -> Unit,
    onLongMovieClick: (MovieSummary) -> Unit,
    favouriteMovieIdsProvider: () -> HashSet<Long>
) {
    Crossfade(
        modifier = Modifier
            .padding(PaddingValues(top = paddingValuesProvider().calculateTopPadding()))
            .navigationBarsPadding(),
        targetState = moviePagingItemsProvider().loadState.refresh,
        label = Constants.EMPTY_STRING
    ) { state ->
        when (state) {
            is LoadState.Error -> ErrorContent(onRetry = {
                moviePagingItemsProvider().refresh()
            })

            LoadState.Loading -> LoadingContent()
            is LoadState.NotLoading -> MovieList(
                shimmerOffsetProvider,
                lazyListStateProvider,
                moviePagingItemsProvider(),
                onMovieClick,
                onLongMovieClick,
                favouriteMovieIdsProvider
            )
        }
    }
}