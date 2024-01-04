package com.example.cryptoview.core.di


import com.example.cryptoview.application.CryptoViewPreferences
import com.example.cryptoview.core.local.LocalDataSource
import com.example.cryptoview.core.local.DefaultLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {
    @Singleton
    @Provides
    fun providePreferences(prefs: CryptoViewPreferences): LocalDataSource {
        return DefaultLocalDataSource(prefs)
    }
}
