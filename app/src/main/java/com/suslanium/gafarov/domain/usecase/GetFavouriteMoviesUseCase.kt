package com.suslanium.gafarov.domain.usecase

import com.suslanium.gafarov.domain.repository.FavouritesRepository

class GetFavouriteMoviesUseCase(
    private val favouritesRepository: FavouritesRepository
) {

    operator fun invoke() = favouritesRepository.getFavoriteMovies()

}