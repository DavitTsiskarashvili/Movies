package com.movies.data.remote.model

import android.graphics.Movie
import android.media.Image
import android.media.Rating
import com.google.gson.annotations.SerializedName

data class MoviesDTO(
    val films: List<Movie>
) {
    data class Movie(
        @SerializedName("film_id")
        val filmId: Int?,
        @SerializedName("imdb_id")
        val imdbId: Int?,
        @SerializedName("imdb_title_id")
        val imdbTitleId: String?,
        @SerializedName("film_name")
        val filmName: String?,
        @SerializedName("other_titles")
        val otherTitles: OtherTitle?,
        @SerializedName("release_dates")
        val releaseDates: List<Date>,
        @SerializedName("age_rating")
        val ageRating: List<Rating>,
        @SerializedName("film_trailer")
        val filmTrailer: String?,
        @SerializedName("synopsis_long")
        val synopsisLong: String?,
        val images: Image,
        val status: Status?
    ) {
        data class OtherTitle(
            @SerializedName("EN")
            val en: String
        )

        data class Date(
            @SerializedName("release_date")
            val releaseDate: String?,
            val notes: String?
        )

        data class Rating(
            val rating: String?,
            @SerializedName("age_rating_image")
            val ageRatingImage: String?,
            @SerializedName("age_advisory")
            val ageAdvisory: String?
        )

        data class Image(
            val poster: `1`,
            val still: `1`
        ) {
            data class `1`(
                @SerializedName("image_orientation")
                val imageOrientation: String?,
                val region: String?,
                val medium: Medium
            ) {
                data class Medium(
                    @SerializedName("film_image")
                    val filmImage: String?,
                    val width: Int?,
                    val height: Int?,
                )
            }
        }
        data class Status(
            val count: Int?,
            val state: String?,
            val method: String?,
            val message: String?,
            @SerializedName("request_method")
            val requestMethod: String?,
            val version: String?,
            val territory: String?,
            @SerializedName("device_datetime_sent")
            val deviceDatetimeSent: String?,
            @SerializedName("device_datetime_used")
            val deviceDatetimeUsed: String?
        )
    }
}


