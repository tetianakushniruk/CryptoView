package com.thecode.cryptoview.core.di


import com.thecode.cryptoview.application.CryptoViewDataStore
import com.thecode.cryptoview.core.local.AppLocalDataSource
import com.thecode.cryptoview.core.local.AppLocalDataSourceImpl
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
