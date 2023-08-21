package com.detailsdomain.repository

import com.commondomain.model.MovieDomainModel

interface MovieDetailsRepository {
    suspend fun fetchMovieDetails(id: Int): MovieDomainModel

}