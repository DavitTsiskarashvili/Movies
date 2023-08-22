package com.movies.presentation.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.movies.presentation.base.data.ui_state.UIStateHandler
import com.movies.presentation.base.view_model.BaseViewModel
import com.movies.presentation.view.error.ErrorView
import com.movies.presentation.view.loader.LoaderDialog
import org.koin.androidx.viewmodel.ext.android.viewModelForClass
import kotlin.reflect.KClass

abstract class BaseFragment<T : Any, VM : BaseViewModel<T>> : Fragment(), UIStateHandler<T> {

    abstract val viewModelClass: KClass<VM>
    abstract val binding: ViewBinding

    lateinit var viewModel: VM

    private val loader by lazy { LoaderDialog(requireContext(), binding.root as ViewGroup) }
    private val errorView by lazy { ErrorView(requireContext(), binding.root as ViewGroup) }

    protected open val layout: Int = 0

    abstract fun onBind()
    open fun onRefresh(){}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelForClass(clazz = viewModelClass).value
        viewModel.onCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (layout != 0) {
            inflater.inflate(layout, container, false)
        } else {
            super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBind()
        observeUIState()
    }

    override fun onError(error: Throwable) {
        errorView.handleErrorVisibility(true)
    }

    override fun onLoading(loading: Boolean) {
        loader.handleLoaderVisibility(loading)
    }

    private fun observeUIState() {
        viewModel.uiStateLiveData.observe(viewLifecycleOwner) {
            handleUIState(it)
        }
    }

    private fun refresh() {
        errorView.refreshButtonListener {
            onRefresh()
        }
    }

}