package com.thecode.cryptoview.core.usecases

import com.thecode.cryptoview.core.domain.OnBoardingPart
import com.thecode.cryptoview.core.repositories.OnBoardingRepository
import javax.inject.Inject

class GetOnBoardingParts @Inject constructor(
    private val repository: OnBoardingRepository
) {
    operator fun invoke(): List<OnBoardingPart> {
        return repository.getOnBoardingList()
    }
}
