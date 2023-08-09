package com.movies.presentation.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.movies.common.extensions.collectFlow
import com.movies.common.navigation.NavigationCommand
import com.movies.common.navigation.observeNonNull
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

    protected abstract val layout: Int

    abstract fun onBind()
    abstract fun onRefresh()

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
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBind()
        observeNavigation()
        observeUIState()
    }

    override fun onError(error: Throwable) {
        errorView.handleErrorVisibility(true)
    }

    override fun onLoading(loading: Boolean) {
        loader.handleLoaderVisibility(loading)
    }

    private fun observeNavigation() {
        viewModel.navigationLiveData.observeNonNull(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { navigationCommand ->
                handleNavigation(navigationCommand)
            }
        }
    }

    private fun handleNavigation(navCommand: NavigationCommand) {
        when (navCommand) {
            is NavigationCommand.ToDirection -> findNavController().navigate(navCommand.directions)
            is NavigationCommand.Back -> findNavController().navigateUp()
        }
    }

    private fun observeUIState() {
        collectFlow(viewModel.uiStateFlow) {
            it?.let { handleUIState(it) }
        }
    }

    private fun refresh() {
        errorView.refreshButtonListener {
            onRefresh()
        }
    }

}