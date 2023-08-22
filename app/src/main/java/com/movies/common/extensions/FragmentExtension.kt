package com.movies.common.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.movies.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun Fragment.executeScope(
    coroutineContext: CoroutineContext = Dispatchers.Unconfined,
    lifecycleState: Lifecycle.State = Lifecycle.State.RESUMED,
    block: suspend () -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch(coroutineContext) {
        repeatOnLifecycle(lifecycleState) { block() }
    }
}

fun <T : Any?> Fragment.collectFlow(
    flow: Flow<T>,
    coroutineContext: CoroutineContext = Dispatchers.Unconfined,
    lifecycleState: Lifecycle.State = Lifecycle.State.RESUMED,
    block: suspend (T) -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch(coroutineContext) {
        flow.flowWithLifecycle(lifecycle, lifecycleState).collect {
            block(it)
        }
    }
}

fun <T> StateFlow<T>.collectLatestInLifecycle(
    lifecycleOwner: LifecycleOwner,
    action: suspend (T) -> Unit
) {
    lifecycleOwner.lifecycleScope.launch {
        this@collectLatestInLifecycle.collectLatest { data ->
            action(data)
        }
    }
}

fun <T> Fragment.observeLiveData(
    liveData: LiveData<T>,
    block: (T) -> Unit
) {
    liveData.observe(viewLifecycleOwner) {
        block(it)
    }
}

fun <T> Fragment.changeScreen(fragment: Fragment, args: T? = null) {
    args?.let {
        val bundle = Bundle().apply {
            putArguments(args)
        }
        fragment.arguments = bundle
    }

    parentFragmentManager.beginTransaction().add(
        R.id.nav_host_fragment, fragment, fragment.tag
    ).addToBackStack(fragment.tag).commit()
}

private fun <T> Bundle.putArguments(args: T) {
    when (args) {
        is Int -> putInt("movieId", args)
    }
}