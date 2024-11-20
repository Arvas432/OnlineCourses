package com.example.onlinecourses.data.network.impl

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.onlinecourses.data.dto.CoursesRequest
import com.example.onlinecourses.data.dto.Response
import com.example.onlinecourses.data.network.NetworkClient
import com.example.onlinecourses.data.network.StepicApi

class RetrofitNetworkClient(
    private val stepicService: StepicApi,
    private val connectivityManager: ConnectivityManager
) : NetworkClient {
    override suspend fun doRequest(request: Any): Response {
        if (!isConnected()) {
            val response = Response(resultCode = NO_CONNECTION)
            return response
        }
        val response = when (request) {
            is CoursesRequest -> getCourses()
            else -> {
                Response(BAD_REQUEST)
            }
        }
        return response
    }

    private suspend fun getCourses(): Response {
        val res = stepicService.searchCourses()
        Log.i("result", res.toString())
        val response = res.body() ?: Response()
        response.resultCode = res.code()
        Log.i("result", response.toString())
        return response
    }

    private fun isConnected(): Boolean {
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return capabilities?.run {
            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        } ?: false
    }


    companion object {
        const val SUCCESS = 200
        const val BAD_REQUEST = 400
        const val NOT_FOUND = 404
        const val NO_CONNECTION = -1
    }
}
