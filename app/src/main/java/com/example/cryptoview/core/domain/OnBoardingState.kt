package com.example.cryptoview.core.domain

sealed class OnBoardingState {
    data class Complete(val list: List<OnBoardingPart>) : OnBoardingState()
}
