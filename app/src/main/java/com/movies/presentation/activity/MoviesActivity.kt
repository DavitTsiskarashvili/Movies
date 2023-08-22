package com.movies.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.commonpresentation.navigation.NavControllerManager
import com.commonpresentation.presentation.base.fragment.ContainerFragment
import com.movies.R
import com.movies.databinding.ActivityMoviesBinding
import org.koin.android.ext.android.inject

class MoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoviesBinding
    private val navControllerManager by inject<NavControllerManager>()
    private val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        installSplashScreen()
        setContentView(binding.root)

        navControllerManager.setNavController(navController)

//        supportFragmentManager.beginTransaction().replace(
//            R.id.nav_host_fragment, ContainerFragment()
//        ).commit()
    }

    private fun getCurrentFragment(): Fragment? {
        return supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
    }

    override fun onBackPressed() {
        when (val currentFragment = getCurrentFragment()) {
            is ContainerFragment -> {
                return currentFragment.onBackPress()
            }
        }
    }

}