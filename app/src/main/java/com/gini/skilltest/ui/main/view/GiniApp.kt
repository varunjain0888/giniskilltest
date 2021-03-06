package com.gini.skilltest.ui.main.view

import android.app.Application
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.gini.skilltest.utils.InternetUtil
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class GiniApp : Application() {
    lateinit var apiClient: OkHttpClient

    override fun onCreate() {
        super.onCreate()
        InternetUtil.init(this)
        Stetho.initializeWithDefaults(this)
    }

    fun getRetrofitClient(): OkHttpClient {
        apiClient = OkHttpClient().newBuilder()
            .addNetworkInterceptor(StethoInterceptor())
            .connectTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .build()
        return apiClient
    }
}