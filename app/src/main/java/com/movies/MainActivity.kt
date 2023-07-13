package com.movies

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.movies.databinding.ActivityMainBinding
import com.movies.databinding.ActivitySplashScreenBinding
import com.movies.presentation.ui.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val splashDuration = 4000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showSplashScreen()
    }

    private fun showSplashScreen() {
        val splashScreenLayout = ActivitySplashScreenBinding.inflate(layoutInflater)
        binding.constraintFrame.addView(splashScreenLayout.root, ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT)

        Handler().postDelayed({
            binding.constraintFrame.removeView(splashScreenLayout.root)

            fragmentSetup()
            finish()
        }, splashDuration)
    }

    private fun fragmentSetup() {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.nav_host_fragment, HomeFragment())
        }.commit()
    }

}
