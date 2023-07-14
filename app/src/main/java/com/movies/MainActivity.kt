package com.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.movies.databinding.ActivityMainBinding
import com.movies.presentation.ui.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val splashDuration = 4000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
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
