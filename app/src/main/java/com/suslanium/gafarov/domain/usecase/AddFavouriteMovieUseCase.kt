package com.suslanium.gafarov.domain.usecase

import com.suslanium.gafarov.domain.entity.MovieSummary
import com.suslanium.gafarov.domain.repository.FavouritesRepository

class AddFavouriteMovieUseCase(
    private val favouritesRepository: FavouritesRepository
) {

    suspend operator fun invoke(movieSummary: MovieSummary) =
        favouritesRepository.addFavoriteMovie(movieSummary)

}