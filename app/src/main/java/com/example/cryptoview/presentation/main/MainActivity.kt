package com.example.cryptoview.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptoview.databinding.ActivityMainBinding
import com.example.cryptoview.utils.FadePageTransformer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.apply {
            viewPager.apply {
                offscreenPageLimit = 3
                adapter = BottomNavPagerAdapter(this@MainActivity)
                isUserInputEnabled = false
                setPageTransformer(FadePageTransformer())
            }
            bottomNavigationBar.setNavigationChangeListener { _, position ->
                viewPager.setCurrentItem(position, true)
            }
        }
    }
}
