package com.suslanium.gafarov.domain.usecase

import com.suslanium.gafarov.domain.repository.MovieRepository

class GetMovieDetailsUseCase(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(movieId: Long) = movieRepository.getMovieDetails(movieId)

}