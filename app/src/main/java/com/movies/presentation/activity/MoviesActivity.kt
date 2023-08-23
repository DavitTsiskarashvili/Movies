package com.movies.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import com.commonpresentation.navigation.NavControllerManager
import com.movies.R
import com.movies.databinding.ActivityMoviesBinding
import org.koin.android.ext.android.inject

class MoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoviesBinding
    private val navControllerManager by inject<NavControllerManager>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        installSplashScreen()
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navControllerManager.setNavController(navController)

    }
}

//        supportFragmentManager.beginTransaction().replace(
//            R.id.nav_host_fragment, ContainerFragment()
//        ).commit()
//    private fun getCurrentFragment(): Fragment? {
//        return supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
//    }
//
//    override fun onBackPressed() {
//        when (val currentFragment = getCurrentFragment()) {
//            is ContainerFragment -> {
//                return currentFragment.onBackPress()
//            }
//        }
//    }