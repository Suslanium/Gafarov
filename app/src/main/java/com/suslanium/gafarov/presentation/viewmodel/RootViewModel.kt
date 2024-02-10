package com.suslanium.gafarov.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.suslanium.gafarov.domain.entity.MovieSummary
import com.suslanium.gafarov.domain.usecase.GetMovieDetailsUseCase
import com.suslanium.gafarov.domain.usecase.GetMoviesListUseCase
import com.suslanium.gafarov.presentation.state.DetailsState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class RootViewModel(
    getMoviesListUseCase: GetMoviesListUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    val movies: Flow<PagingData<MovieSummary>> = getMoviesListUseCase().cachedIn(viewModelScope)

    val detailsState: State<DetailsState>
        get() = _detailsState
    private val _detailsState = mutableStateOf<DetailsState>(DetailsState.Empty)

    private val _selectedMovieId = mutableStateOf<Long?>(null)

    val isDetailsScreenShown: State<Boolean>
        get() = _isDetailsScreenShown
    private val _isDetailsScreenShown = mutableStateOf(false)

    private val loadingExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _detailsState.value = DetailsState.Error
    }

    fun hideDetails() {
        _isDetailsScreenShown.value = false
    }

    fun setSelectedMovieId(movieId: Long) {
        _isDetailsScreenShown.value = true
        if (movieId == _selectedMovieId.value) return
        _selectedMovieId.value = movieId
        loadData()
    }

    fun loadData() {
        _detailsState.value = DetailsState.Loading
        viewModelScope.launch(Dispatchers.IO + loadingExceptionHandler) {
            val movieDetails = getMovieDetailsUseCase(_selectedMovieId.value!!)
            _detailsState.value = DetailsState.Content(movieDetails)
        }
    }

}