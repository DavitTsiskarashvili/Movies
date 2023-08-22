package com.movies.presentation.navigation

import androidx.navigation.NavController
import com.commonpresentation.navigation.NavControllerManager

class NavControllerManagerImpl(): NavControllerManager {
    private lateinit var navController: NavController

    override fun setNavController(navController: NavController) {
       this.navController = navController
    }

    override fun getNavController(): NavController {
        return navController
    }
}