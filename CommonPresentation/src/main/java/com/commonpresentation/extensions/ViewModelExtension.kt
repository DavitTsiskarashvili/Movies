package com.commonpresentation.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

fun ViewModel.viewModelScope(block: suspend () -> Unit) {
    viewModelScope.launch { block() }
}