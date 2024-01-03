package com.example.cryptoview.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cryptoview.presentation.main.home.HomeFragment
import com.example.cryptoview.presentation.main.markets.MarketsFragment
import com.example.cryptoview.presentation.main.wallet.WalletFragment

class BottomNavPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> MarketsFragment()
            else -> WalletFragment()
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}
