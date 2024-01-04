package com.example.cryptoview.core.local

import com.example.cryptoview.application.CryptoViewPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LocalDataSource {

    suspend fun setOnboardingCompleted()

    fun isOnboardingCompleted(): Flow<Boolean>

    suspend fun clearAppData()
}

class DefaultLocalDataSource @Inject constructor(
    private val prefs: CryptoViewPreferences
) : LocalDataSource {

    override suspend fun setOnboardingCompleted() {
        prefs.setOnboardingCompleted()
    }

    override fun isOnboardingCompleted(): Flow<Boolean> {
        return prefs.isOnboardingCompleted()
    }

    override suspend fun clearAppData() {
        prefs.clearSession()
    }
}
