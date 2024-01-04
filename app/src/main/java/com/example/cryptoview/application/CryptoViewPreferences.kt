package com.example.cryptoview.application

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.cryptoview.utils.AppConstants.PREFERENCE_NAME
import com.example.cryptoview.utils.extensions.getValueFlow
import com.example.cryptoview.utils.extensions.setValue
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private val Context.prefs: DataStore<Preferences> by preferencesDataStore(PREFERENCE_NAME)

class CryptoViewPreferences @Inject constructor(@ApplicationContext context: Context) {

    companion object {
        private const val IS_ONBOARDING_COMPLETED = "IS_ONBOARDING_COMPLETED"
    }

    private val prefs = context.prefs

    suspend fun setOnboardingCompleted() {
        val prefsKey = booleanPreferencesKey(IS_ONBOARDING_COMPLETED)
        prefs.setValue(prefsKey, true)
    }

    fun isOnboardingCompleted(): Flow<Boolean> {
        val prefsKey = booleanPreferencesKey(IS_ONBOARDING_COMPLETED)
        return prefs.getValueFlow(prefsKey, false)
    }

    suspend fun clearSession() {
        prefs.edit { preferences ->
            preferences.clear()
        }
    }
}
