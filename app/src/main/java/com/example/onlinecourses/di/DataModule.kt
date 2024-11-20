package com.example.onlinecourses.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.onlinecourses.data.network.NetworkClient
import com.example.onlinecourses.data.network.StepicApi
import com.example.onlinecourses.data.network.impl.RetrofitNetworkClient
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val dataModule = module {
    single<StepicApi> {
        val gson = GsonBuilder().create()
        Retrofit.Builder().baseUrl("https://stepik.org/api/").addConverterFactory(GsonConverterFactory.create(gson)).build().create(StepicApi::class.java)
    }
    factory { Gson() }
    single<ConnectivityManager> {
        androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
    single<NetworkClient> {
        RetrofitNetworkClient(get(), get())
    }
}