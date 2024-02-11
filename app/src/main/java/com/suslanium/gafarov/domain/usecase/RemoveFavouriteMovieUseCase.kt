package com.suslanium.gafarov.domain.usecase

import com.suslanium.gafarov.domain.repository.FavouritesRepository

class RemoveFavouriteMovieUseCase(
    private val favouritesRepository: FavouritesRepository
) {

    suspend operator fun invoke(movieId: Long) = favouritesRepository.removeFavoriteMovie(movieId)

}