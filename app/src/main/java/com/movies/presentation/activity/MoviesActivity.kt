package com.movies.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.movies.R
import com.movies.databinding.ActivityMoviesBinding
import com.movies.presentation.base.fragment.BaseChildFragment

class MoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        installSplashScreen()
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(
            R.id.nav_host_fragment, BaseChildFragment()
        ).commit()
    }

    private fun getCurrentFragment(): Fragment? {
        return supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
    }

    override fun onBackPressed() {
        when (val currentFragment = getCurrentFragment()) {
            is BaseChildFragment -> {
                return currentFragment.onBackPress()
            }
        }
    }

}