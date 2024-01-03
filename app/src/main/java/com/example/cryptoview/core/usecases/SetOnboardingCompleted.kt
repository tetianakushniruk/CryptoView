package com.example.cryptoview.core.usecases

import com.example.cryptoview.core.repositories.OnBoardingRepository
import javax.inject.Inject

class SetOnboardingCompleted @Inject constructor(
    private val repository: OnBoardingRepository
) {
    suspend operator fun invoke() {
        return repository.setOnboardingCompleted()
    }
}
