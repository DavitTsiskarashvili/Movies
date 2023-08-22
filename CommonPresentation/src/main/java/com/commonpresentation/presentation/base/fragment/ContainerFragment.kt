package com.commonpresentation.presentation.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.commonpresentation.R
import com.commonpresentation.databinding.BaseFragmentBinding
import com.commonpresentation.extensions.replaceView
import com.commonpresentation.presentation.view.navigation.NavigationView

class ContainerFragment : Fragment() {

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
        if (getCurrentFragment().needPressBack().not()) return

        if (childFragmentManager.backStackEntryCount == 0) {
            requireActivity().finish()
            return
        }
        childFragmentManager.popBackStack()
    }

    private fun getCurrentFragment() =
        childFragmentManager.findFragmentById(R.id.childFragmentContainerView) as BaseFragment<*, *>

    fun configureBottomView(fragment: BaseFragment<*, *> = getCurrentFragment()) = with(fragment) {
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
        childBinding.bottomContainer.replaceView(getCurrentFragment().showBottomView(), view)
    }
}