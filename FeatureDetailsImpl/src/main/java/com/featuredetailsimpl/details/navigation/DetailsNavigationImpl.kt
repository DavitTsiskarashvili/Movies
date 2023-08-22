package com.featuredetailsimpl.details.navigation

import android.os.Bundle
import com.commonpresentation.navigation.NavControllerManager
import com.featuredetailsapi.navigation.DetailsNavigationApi
import com.featuredetailsimpl.R

class DetailsNavigationImpl(private val navControllerManager: NavControllerManager) :
    DetailsNavigationApi {
    override fun navigateToDetails(bundle: Bundle) {
        navControllerManager.getNavController().navigate(R.id.details_nav_graph, bundle)
    }
}
