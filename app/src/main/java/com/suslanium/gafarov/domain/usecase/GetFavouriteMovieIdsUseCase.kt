package com.suslanium.gafarov.domain.usecase

import com.suslanium.gafarov.domain.repository.FavouritesRepository

class GetFavouriteMovieIdsUseCase(
    private val favouritesRepository: FavouritesRepository
) {

    operator fun invoke() = favouritesRepository.getFavoriteMovieIds()

}