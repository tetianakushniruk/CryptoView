package com.example.cryptoview.presentation.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.example.cryptoview.R
import com.example.cryptoview.core.domain.OnBoardingState
import com.example.cryptoview.databinding.ActivityOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {

    private val onboardingViewModel: OnboardingViewModel by viewModels()
    private val onboardingAdapter = OnBoardingAdapter()
    private lateinit var onboardingBinding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onboardingBinding = ActivityOnboardingBinding.inflate(layoutInflater)
        val rootView = onboardingBinding.root
        setContentView(rootView)
        initializeViews()
        observeViewModel()
        onboardingViewModel.getOnBoardingSlide()
    }

    private fun initializeViews() {
        onboardingBinding.apply {
            btnNextStep.setOnClickListener {
                if (getNextItemPosition() > getAdapterSize()) {
                    launchMainScreen()
                } else {
                    viewPager.setCurrentItem(getNextItemPosition(), true)
                }
            }

            textSkip.setOnClickListener {
                viewPager.currentItem = getAdapterSize()
            }
        }
        setupViewPager()
    }

    private fun setupViewPager() {
        onboardingBinding.apply {
            viewPager.apply {
                adapter = onboardingAdapter
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        btnNextStep.text =
                            if (position == LAST_PAGE_POSITION) getString(R.string.finish) else getString(R.string.next)
                    }
                })
            }
            TabLayoutMediator(pageIndicator, viewPager) { _, _ -> }.attach()
        }
    }

    private fun observeViewModel() {
        onboardingViewModel.state.observe(
            this
        ) {
            when (it) {
                is OnBoardingState.Complete -> onboardingAdapter.setItem(it.list)
            }
        }
    }

    private fun launchMainScreen() {
        onboardingViewModel.setOnboardingCompleted()
        val mainScreenIntent = Intent(applicationContext, OnboardingFinishActivity::class.java)
        startActivity(mainScreenIntent)
        finishAffinity()
    }

    private fun getNextItemPosition(): Int {
        return onboardingBinding.viewPager.currentItem + 1
    }

    private fun getAdapterSize(): Int {
        return onboardingAdapter.itemCount - 1
    }

    companion object {
        private const val LAST_PAGE_POSITION = 2
    }
}
