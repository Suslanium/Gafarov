package com.suslanium.gafarov.presentation.ui.screen

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.suslanium.gafarov.R
import com.suslanium.gafarov.domain.entity.MovieSummary
import com.suslanium.gafarov.presentation.Constants
import com.suslanium.gafarov.presentation.state.RootState
import com.suslanium.gafarov.presentation.ui.common.SegmentedBottomBarButton
import com.suslanium.gafarov.presentation.ui.screen.favourites.FavouritesScreen
import com.suslanium.gafarov.presentation.ui.screen.main.MainScreen
import com.suslanium.gafarov.presentation.ui.theme.Black
import com.suslanium.gafarov.presentation.ui.theme.S25_W600
import com.suslanium.gafarov.presentation.ui.theme.Surface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootScreenContainer(
    shimmerOffsetProvider: () -> Float,
    moviePagingItemsProvider: () -> LazyPagingItems<MovieSummary>,
    favouriteMoviesProvider: () -> List<MovieSummary>,
    mainLazyListStateProvider: () -> LazyListState,
    favouritesLazyListStateProvider: () -> LazyListState,
    widthFraction: Float = 1f,
    onMovieClick: (Long) -> Unit = {},
    onLongMovieClick: (MovieSummary) -> Unit = {},
    favouriteMovieIdsProvider: () -> HashSet<Long>,
    rootStateProvider: () -> RootState,
    onSetRootState: (RootState) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(modifier = Modifier
        .fillMaxWidth(widthFraction)
        .nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
        TopAppBar(
            title = {
                Text(
                    text = if (rootStateProvider() == RootState.MAIN) stringResource(id = R.string.popular)
                    else stringResource(id = R.string.favourites),
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
    }, bottomBar = {
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .background(
                    color = Color.Transparent
                )
                .padding(all = 16.dp)
                .navigationBarsPadding()
                .fillMaxWidth()
        ) {
            SegmentedBottomBarButton(text = stringResource(id = R.string.popular),
                selected = rootStateProvider() == RootState.MAIN,
                onClick = { onSetRootState(RootState.MAIN) })
            Spacer(modifier = Modifier.width(15.dp))
            SegmentedBottomBarButton(text = stringResource(id = R.string.favourites),
                selected = rootStateProvider() == RootState.FAVOURITES,
                onClick = { onSetRootState(RootState.FAVOURITES) })
        }
    }) { paddingValues ->
        Crossfade(targetState = rootStateProvider(), label = Constants.EMPTY_STRING) {
            when (it) {
                RootState.MAIN -> MainScreen(
                    { paddingValues },
                    moviePagingItemsProvider,
                    shimmerOffsetProvider,
                    mainLazyListStateProvider,
                    onMovieClick,
                    onLongMovieClick,
                    favouriteMovieIdsProvider
                )

                RootState.FAVOURITES -> FavouritesScreen(
                    paddingValuesProvider = { paddingValues },
                    movieListProvider = favouriteMoviesProvider,
                    shimmerOffsetProvider = shimmerOffsetProvider,
                    lazyListStateProvider = favouritesLazyListStateProvider,
                    onMovieClick = onMovieClick,
                    onLongMovieClick = onLongMovieClick
                )
            }
        }
    }
}

