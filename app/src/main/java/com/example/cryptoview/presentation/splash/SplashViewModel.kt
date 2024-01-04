package com.example.cryptoview.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptoview.core.usecases.IsOnboardingCompleted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val isOnboardingCompleted: IsOnboardingCompleted
) : ViewModel() {
    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean>
        get() = _state

    fun isOnboardingCompleted(): Boolean {
        val isCompleted: Boolean
        runBlocking {
            isCompleted = isOnboardingCompleted.invoke().first()
        }
        _state.value = isCompleted
        return isCompleted
    }
}
