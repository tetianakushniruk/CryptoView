package com.example.cryptoview.core.usecases

import com.example.cryptoview.core.repositories.OnBoardingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsOnboardingCompleted @Inject constructor(
    private val repository: OnBoardingRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return repository.isOnboardingCompleted()
    }
}
