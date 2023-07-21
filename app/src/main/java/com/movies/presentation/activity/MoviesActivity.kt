package com.movies.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.movies.R
import com.movies.databinding.ActivityMoviesBinding
import com.movies.presentation.home.ui.HomeFragment

class MoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        installSplashScreen()
        setContentView(binding.root)

        fragmentSetup()
    }

    private fun fragmentSetup() {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.nav_host_fragment, HomeFragment())
        }.commit()
    }

}
