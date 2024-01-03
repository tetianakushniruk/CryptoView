package com.example.cryptoview.core.di


import com.example.cryptoview.application.CryptoViewDataStore
import com.example.cryptoview.core.local.AppLocalDataSource
import com.example.cryptoview.core.local.AppLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object DataModule {
    @Singleton
    @Provides
    fun provideInotifyDataStore(dataStore: CryptoViewDataStore): AppLocalDataSource {
        return AppLocalDataSourceImpl(dataStore)
    }
}
