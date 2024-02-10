package com.suslanium.gafarov.presentation.ui.screen.main

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.suslanium.gafarov.R
import com.suslanium.gafarov.domain.entity.MovieSummary
import com.suslanium.gafarov.presentation.Constants
import com.suslanium.gafarov.presentation.ui.common.ErrorContent
import com.suslanium.gafarov.presentation.ui.common.LoadingContent
import com.suslanium.gafarov.presentation.ui.screen.main.components.MovieList
import com.suslanium.gafarov.presentation.ui.theme.Black
import com.suslanium.gafarov.presentation.ui.theme.S25_W600
import com.suslanium.gafarov.presentation.ui.theme.Surface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    shimmerOffsetProvider: () -> Float,
    moviePagingItemsProvider: () -> LazyPagingItems<MovieSummary>,
    lazyListStateProvider: () -> LazyListState,
    widthFraction: Float = 1f,
    onMovieClick: (Long) -> Unit = {}
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(modifier = Modifier
        .fillMaxWidth(widthFraction)
        .nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.popular),
                    style = S25_W600,
                    textAlign = TextAlign.Start,
                    color = Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }, scrollBehavior = scrollBehavior, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Surface, scrolledContainerColor = Surface
            )
        )
    }) { paddingValues ->
        Crossfade(
            modifier = Modifier.padding(paddingValues),
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
                    onMovieClick
                )
            }
        }
    }
}

