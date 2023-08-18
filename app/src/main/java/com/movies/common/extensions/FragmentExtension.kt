package com.movies.common.extensions

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.movies.R
import com.movies.presentation.base.fragment.BaseFragment
import com.movies.presentation.base.fragment.ContainerFragment
import com.movies.presentation.utils.NavigationConstants.MOVIE_ID
import kotlinx.coroutines.Dispatchers
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

fun <T> Fragment.observeLiveData(
    liveData: LiveData<T>,
    block: (T) -> Unit
) {
    liveData.observe(viewLifecycleOwner) {
        block(it)
    }
}

fun BaseFragment<*, *>.tryConfigureBottomView(tryAction: () -> Unit) {
    try {
        tryAction.invoke()
    } catch (_: Exception) {
        (parentFragment as ContainerFragment).addBottomContainerView(bottomView())
    }
}

fun <T> Fragment.changeScreen(fragment: Fragment, args: T? = null) {
    args?.let {
        val bundle = Bundle().apply {
            putArguments(MOVIE_ID, args)
        }
        fragment.arguments = bundle
    }

    parentFragmentManager.beginTransaction().add(
        R.id.childFragmentContainerView, fragment
    ).addToBackStack(null).commit()
}

@Suppress("UNCHECKED_CAST")
fun <T> Bundle.putArguments(key: String, args: T? = null) {
    when (args) {
        is Int -> putInt(key, args)
        is String -> putString(key, args)
        is ArrayList<*> -> putStringArrayList(key, args as ArrayList<String>)
        is Parcelable -> putParcelable(key, args)
    }
}

fun Fragment.hideKeyboard() {
    val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val currentFocusedView = requireActivity().currentFocus
    if (currentFocusedView != null) {
        imm.hideSoftInputFromWindow(currentFocusedView.windowToken, 0)
    }
}