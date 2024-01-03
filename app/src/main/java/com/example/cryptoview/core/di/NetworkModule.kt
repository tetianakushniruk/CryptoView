package com.example.cryptoview.core.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.example.cryptoview.BuildConfig
import com.example.cryptoview.core.remote.AppRemoteDataSourceImpl
import com.example.cryptoview.datasource.CoinGeckoApiRemoteService
import com.example.cryptoview.datasource.CoinGeckoApiRemoteServiceImpl
import com.example.cryptoview.datasource.network.api.CoinGeckoApi
import com.example.cryptoview.datasource.network.mapper.CoinMapper
import com.example.cryptoview.datasource.network.mapper.ExchangeMapper
import com.example.cryptoview.datasource.network.mapper.MarketChartMapper
import com.example.cryptoview.utils.AppConstants
import com.example.cryptoview.utils.AppConstants.REQUEST_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(AppConstants.COINGECKOAPI_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(getOkHttpService())
    }

    @Singleton
    @Provides
    fun provideWhoService(retrofit: Retrofit.Builder): CoinGeckoApi {
        return retrofit
            .build()
            .create(CoinGeckoApi::class.java)
    }

    @Singleton
    @Provides
    fun provideInfotifyRemoteService(
        api: CoinGeckoApi
    ): CoinGeckoApiRemoteService {
        return CoinGeckoApiRemoteServiceImpl(api)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        coinGeckoRemoteService: CoinGeckoApiRemoteService,
        coinMapper: CoinMapper,
        exchangeMapper: ExchangeMapper,
        marketChartMapper: MarketChartMapper
    ): AppRemoteDataSourceImpl {
        return AppRemoteDataSourceImpl(
            coinGeckoRemoteService,
            coinMapper,
            exchangeMapper,
            marketChartMapper
        )
    }

    private fun getOkHttpService(): OkHttpClient {
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            httpClient.addInterceptor(interceptor)
        }

        return httpClient.build()
    }


}
