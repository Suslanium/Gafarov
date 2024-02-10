package com.suslanium.gafarov.presentation.ui.screen

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.suslanium.gafarov.domain.entity.MovieSummary
import com.suslanium.gafarov.presentation.Constants
import com.suslanium.gafarov.presentation.ui.common.WindowInfo
import com.suslanium.gafarov.presentation.ui.common.rememberWindowInfo
import com.suslanium.gafarov.presentation.ui.common.shimmerOffsetAnimation
import com.suslanium.gafarov.presentation.ui.screen.details.DetailsScreen
import com.suslanium.gafarov.presentation.ui.screen.main.MainScreen
import com.suslanium.gafarov.presentation.viewmodel.RootViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RootComponent(context: Context) {
    val windowInfo = rememberWindowInfo()
    val movieListState = rememberLazyListState()
    val viewModel: RootViewModel = koinViewModel()
    val moviePagingItems: LazyPagingItems<MovieSummary> =
        viewModel.movies.collectAsLazyPagingItems()
    val detailsState by remember { viewModel.detailsState }
    val detailsScreenIsShown by remember { viewModel.isDetailsScreenShown }
    val transition = rememberInfiniteTransition(label = Constants.EMPTY_STRING)
    val startOffsetX by shimmerOffsetAnimation(transition)

    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
        Crossfade(targetState = detailsScreenIsShown, label = Constants.EMPTY_STRING) { shown ->
            if (shown) {
                DetailsScreen(
                    shimmerOffsetProvider = { startOffsetX },
                    context = context,
                    detailsState = detailsState,
                    onRetry = viewModel::loadData
                )
                BackHandler(onBack = viewModel::hideDetails)
            } else {
                MainScreen(
                    shimmerOffsetProvider = { startOffsetX },
                    moviePagingItemsProvider = {
                        moviePagingItems
                    },
                    lazyListStateProvider = { movieListState },
                    onMovieClick = viewModel::setSelectedMovieId
                )
            }
        }
    } else {
        Row(modifier = Modifier.fillMaxSize()) {
            MainScreen(
                shimmerOffsetProvider = { startOffsetX },
                moviePagingItemsProvider = { moviePagingItems },
                lazyListStateProvider = { movieListState },
                onMovieClick = viewModel::setSelectedMovieId,
                widthFraction = 0.5f
            )
            DetailsScreen(
                shimmerOffsetProvider = { startOffsetX },
                context = context,
                detailsState = detailsState,
                onRetry = viewModel::loadData
            )
        }
    }
}