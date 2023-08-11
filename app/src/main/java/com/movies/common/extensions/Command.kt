package com.movies.common.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData

class Command<T> {

    private val liveData = MutableLiveData<T?>()

    fun observe(lifecycleOwner: LifecycleOwner, callback: (T)-> Unit) {
        liveData.observe(lifecycleOwner) {
            if (it != null) {
                callback(it)
                liveData.value = null
            }
        }
    }

    fun setValue(value: T) {
        liveData.value = value
    }

    fun postValue(value: T) {
        liveData.postValue(value)
    }
}