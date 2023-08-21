package com.commondomain.model

import com.commondomain.NetworkConstants.IMAGE_BASE_URL
import com.commondomain.extensions.toHoursAndMinutes
import java.math.BigDecimal
import java.math.RoundingMode

data class MovieDomainModel(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val overview: String,
    var isFavourite: Boolean,
    val duration: Int? = null,
    val genreInt: Int? = null,
    var genreString: String? = null,
    private val rating: Double,
    private val poster: String,
){
    fun getFormattedRating(): Double {
        return BigDecimal(rating).setScale(1, RoundingMode.HALF_EVEN).toDouble()
    }

    fun getFullPoster(): String {
        return IMAGE_BASE_URL + poster
    }

    fun getFormattedRuntime(): String = duration?.toHoursAndMinutes() ?: ""

}