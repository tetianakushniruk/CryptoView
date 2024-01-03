package com.example.cryptoview.application

import android.app.Application
import android.content.Context
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CryptoViewApp : Application(), LifecycleObserver {

    private lateinit var mContext: Context

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
        CryptoViewDataStore(mContext)

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }
}
