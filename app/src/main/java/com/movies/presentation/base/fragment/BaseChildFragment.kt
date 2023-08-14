package com.movies.presentation.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.movies.R
import com.movies.common.extensions.replaceView
import com.movies.databinding.BaseFragmentBinding
import com.movies.presentation.home.ui.HomeFragment
import com.movies.presentation.view.navigation.NavigationView

class BaseChildFragment : Fragment() {

    private lateinit var childBinding: BaseFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return BaseFragmentBinding.inflate(inflater, container, false).also {
            childBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpFragment()
    }

    private fun setUpFragment() {
        childFragmentManager.beginTransaction().replace(
            R.id.childFragmentContainerView, HomeFragment(),
        ).commit()
    }

    fun onBackPress() {
        if (currentFragment().needPressBack().not()) return

        if (childFragmentManager.backStackEntryCount == 0) {
            requireActivity().finish()
            return
        }
        childFragmentManager.popBackStack()
    }

    private fun currentFragment() =
        childFragmentManager.findFragmentById(R.id.childFragmentContainerView) as BaseFragment<*, *>


    fun configureBottomView(fragment: BaseFragment<*, *> = currentFragment()) = with(fragment) {
        (bottomView() as NavigationView).apply {
            leftButtonListener {
                defaultLeftButtonAction()
            }
            rightButtonListener {
                defaultRightButtonAction()
            }
            addBottomContainerView(this)
            setButtonsActiveStatus(activeNavigationButton())
        }
    }

    fun addBottomContainerView(view: View) {
        childBinding.bottomContainer.replaceView(currentFragment().showBottomView(), view)
    }
}