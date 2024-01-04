package com.example.cryptoview.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cryptoview.presentation.main.home.HomeFragment
import com.example.cryptoview.presentation.main.markets.MarketsFragment

class BottomNavPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            else -> MarketsFragment()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}
