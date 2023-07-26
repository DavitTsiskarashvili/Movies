package com.movies.domain.model

import com.movies.data.remote.NetworkConstants.IMAGE_BASE_URL
import java.math.BigDecimal
import java.math.RoundingMode

data class MovieDomainModel(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val overview: String,
    var isFavourite: Boolean,
    private val rating: Double,
    private val poster: String,
){
    fun getFormattedRating(): Double {
        return BigDecimal(rating).setScale(1, RoundingMode.HALF_EVEN).toDouble()
    }

    fun getFullPoster(): String {
        return IMAGE_BASE_URL + poster
    }

}