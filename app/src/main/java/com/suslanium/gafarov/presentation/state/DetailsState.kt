package com.suslanium.gafarov.presentation.state

import com.suslanium.gafarov.domain.entity.MovieDetails

sealed interface DetailsState {

    data object Empty : DetailsState

    data object Loading : DetailsState

    data object Error : DetailsState

    data class Content(val content: MovieDetails) : DetailsState

}