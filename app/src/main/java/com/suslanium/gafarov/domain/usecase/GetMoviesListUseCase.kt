package com.suslanium.gafarov.domain.usecase

import com.suslanium.gafarov.domain.repository.MovieRepository

class GetMoviesListUseCase(
    private val movieRepository: MovieRepository
) {

    operator fun invoke() = movieRepository.getMoviesList()

}