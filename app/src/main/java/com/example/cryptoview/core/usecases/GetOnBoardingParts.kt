package com.example.cryptoview.core.usecases

import com.example.cryptoview.core.domain.OnBoardingPart
import com.example.cryptoview.core.repositories.OnBoardingRepository
import javax.inject.Inject

class GetOnBoardingParts @Inject constructor(
    private val repository: OnBoardingRepository
) {
    operator fun invoke(): List<OnBoardingPart> {
        return repository.getOnBoardingList()
    }
}
