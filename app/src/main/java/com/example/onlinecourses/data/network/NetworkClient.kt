package com.example.onlinecourses.data.network

import com.example.onlinecourses.data.dto.Response

interface NetworkClient {
    suspend fun doRequest(request: Any): Response
}
