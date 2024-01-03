package com.example.cryptoview.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoview.core.usecases.IsOnboardingCompleted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val isOnboardingCompleted: IsOnboardingCompleted
) : ViewModel() {
    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean>
        get() = _state

    fun isOnboardingCompleted(): Boolean {
        viewModelScope.launch {
            _state.value.let { _ ->
                isOnboardingCompleted.invoke().collect {
                    _state.value = it
                }
            }
        }
        return _state.value == true
    }
}
