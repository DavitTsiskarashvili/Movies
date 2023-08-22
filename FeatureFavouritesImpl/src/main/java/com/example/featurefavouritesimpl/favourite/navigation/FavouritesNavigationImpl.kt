package com.example.featurefavouritesimpl.favourite.navigation

import com.commonpresentation.navigation.NavControllerManager
import com.example.featurefavouritesimpl.R
import com.featurefavouritesapi.navigation.FavouritesNavigationApi

class FavouritesNavigationImpl(private val navControllerManager: NavControllerManager) :
    FavouritesNavigationApi {
    override fun navigateToFavourites() {
        navControllerManager.getNavController().navigate(R.id.favourites_nav_graph)
    }
}
