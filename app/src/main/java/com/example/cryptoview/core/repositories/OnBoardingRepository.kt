package com.example.cryptoview.core.repositories

import com.example.cryptoview.R
import com.example.cryptoview.core.domain.OnBoardingPart
import com.example.cryptoview.core.local.AppLocalDataSourceImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OnBoardingRepository @Inject constructor(
    private val localDataSource: AppLocalDataSourceImpl
) {

    suspend fun setOnboardingCompleted() {
        localDataSource.setOnboardingCompleted()
    }

    fun isOnboardingCompleted(): Flow<Boolean> {
        return localDataSource.isOnboardingCompleted()
    }

    fun getOnBoardingList(): List<OnBoardingPart> {
        return listOf(
            OnBoardingPart(
                title = R.string.title_onboarding_1,
                description = R.string.description_onboarding_1
            ),
            OnBoardingPart(
                title = R.string.title_onboarding_2,
                description = R.string.description_onboarding_2
            ),
            OnBoardingPart(
                title = R.string.title_onboarding_3,
                description = R.string.description_onboarding_3
            )
        )
    }
}
