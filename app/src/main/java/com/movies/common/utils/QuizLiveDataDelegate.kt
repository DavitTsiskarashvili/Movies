package com.movies.common.utils

import androidx.lifecycle.LiveData
import kotlin.reflect.KProperty

class LiveDataDelegate<T> : LiveData<T>() {

    operator fun getValue(
        thisRef: Any,
        property: KProperty<*>
    ): LiveDataDelegate<T> {
        return this
    }

    internal fun addValue(value: T) {
        postValue(value)
    }
}
