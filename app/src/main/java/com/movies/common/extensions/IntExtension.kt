package com.movies.common.extensions

fun Int.toHoursAndMinutes(): String {
    val hours = this / 60
    val minutes = this % 60
    return "${hours}h ${minutes}m"
}