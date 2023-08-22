package com.example.featurehomeimpl.navigation

import com.commonpresentation.navigation.NavControllerManager
import com.example.featurehomeimpl.R
import com.homeapi.navigation.HomeNavigationApi

class HomeNavigationImpl(private val navControllerManager: NavControllerManager) :
    HomeNavigationApi {
    override fun navigateToHome() {
        navControllerManager.getNavController().navigate(R.id.home_nav_graph)
    }
}