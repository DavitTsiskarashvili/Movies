package com.commonpresentation.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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

//fun BaseFragment<*, *>.tryConfigureBottomView(tryAction: () -> Unit) {
//    try {
//        tryAction.invoke()
//    } catch (_: Exception) {
//        (parentFragment as ContainerFragment).addBottomContainerView(bottomView())
//    }
//}
//
//fun <T> Fragment.changeScreen(fragment: Fragment, args: T? = null) {
//    args?.let {
//        val bundle = Bundle().apply {
//            putArguments(MOVIE_ID, args)
//        }
//        fragment.arguments = bundle
//    }
//
//    parentFragmentManager.beginTransaction().add(
//        R.id.childFragmentContainerView, fragment
//    ).addToBackStack(null).commit()
//}
//
//@Suppress("UNCHECKED_CAST")
//fun <T> Bundle.putArguments(key: String, args: T? = null) {
//    when (args) {
//        is Int -> putInt(key, args)
//        is String -> putString(key, args)
//        is ArrayList<*> -> putStringArrayList(key, args as ArrayList<String>)
//        is Parcelable -> putParcelable(key, args)
//    }
//}