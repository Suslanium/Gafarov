package com.suslanium.gafarov.domain.usecase

import com.suslanium.gafarov.domain.entity.MovieDetails
import com.suslanium.gafarov.domain.repository.FavouritesRepository

class SaveMovieDetailsUseCase(
    private val favouritesRepository: FavouritesRepository
) {

    suspend operator fun invoke(movieId: Long, movieDetails: MovieDetails) =
        favouritesRepository.saveMovieDetails(movieId, movieDetails)

}