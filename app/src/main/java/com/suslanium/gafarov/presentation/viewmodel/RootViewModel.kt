package com.suslanium.gafarov.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.suslanium.gafarov.domain.entity.MovieSummary
import com.suslanium.gafarov.domain.usecase.AddFavouriteMovieUseCase
import com.suslanium.gafarov.domain.usecase.GetFavouriteMovieIdsUseCase
import com.suslanium.gafarov.domain.usecase.GetFavouriteMoviesUseCase
import com.suslanium.gafarov.domain.usecase.GetMovieDetailsUseCase
import com.suslanium.gafarov.domain.usecase.GetMoviesListUseCase
import com.suslanium.gafarov.domain.usecase.RemoveFavouriteMovieUseCase
import com.suslanium.gafarov.domain.usecase.SaveMovieDetailsUseCase
import com.suslanium.gafarov.presentation.state.DetailsState
import com.suslanium.gafarov.presentation.state.RootState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RootViewModel(
    getMoviesListUseCase: GetMoviesListUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val addFavouriteMovieUseCase: AddFavouriteMovieUseCase,
    private val saveMovieDetailsUseCase: SaveMovieDetailsUseCase,
    getFavouriteMoviesUseCase: GetFavouriteMoviesUseCase,
    getFavouriteMovieIdsUseCase: GetFavouriteMovieIdsUseCase,
    private val removeFavouriteMovieUseCase: RemoveFavouriteMovieUseCase
) : ViewModel() {

    val movies: Flow<PagingData<MovieSummary>> = getMoviesListUseCase().cachedIn(viewModelScope)

    val detailsState: State<DetailsState>
        get() = _detailsState
    private val _detailsState = mutableStateOf<DetailsState>(DetailsState.Empty)

    val rootState: State<RootState>
        get() = _rootState
    private val _rootState = mutableStateOf(RootState.MAIN)

    private val _selectedMovieId = mutableStateOf<Long?>(null)

    val isDetailsScreenShown: State<Boolean>
        get() = _isDetailsScreenShown
    private val _isDetailsScreenShown = mutableStateOf(false)

    val favouriteMovieIds = getFavouriteMovieIdsUseCase().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), hashSetOf()
    )

    val favouriteMovies = getFavouriteMoviesUseCase().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), listOf()
    )

    private val loadingExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _detailsState.value = DetailsState.Error
    }

    private val ignoreExceptionHandler = CoroutineExceptionHandler { _, _ -> }

    fun hideDetails() {
        _isDetailsScreenShown.value = false
    }

    fun setSelectedMovieId(movieId: Long) {
        _isDetailsScreenShown.value = true
        if (movieId == _selectedMovieId.value) return
        _selectedMovieId.value = movieId
        loadDetails()
    }

    fun loadDetails() {
        _detailsState.value = DetailsState.Loading
        viewModelScope.launch(Dispatchers.IO + loadingExceptionHandler) {
            val movieDetails = getMovieDetailsUseCase(_selectedMovieId.value!!)
            _detailsState.value = DetailsState.Content(movieDetails)
        }
    }

    fun setRootState(state: RootState) {
        if (state == _rootState.value) return
        _rootState.value = state
    }

    fun addOrRemoveFavouriteMovie(movieSummary: MovieSummary) {
        if (!favouriteMovieIds.value.contains(movieSummary.id))
            viewModelScope.launch(Dispatchers.IO + ignoreExceptionHandler) {
                addFavouriteMovieUseCase(movieSummary)
                val movieDetails = getMovieDetailsUseCase(movieSummary.id)
                saveMovieDetailsUseCase(movieSummary.id, movieDetails)
            }
        else
            viewModelScope.launch(Dispatchers.IO + ignoreExceptionHandler) {
                removeFavouriteMovieUseCase(movieSummary.id)
            }
    }

}