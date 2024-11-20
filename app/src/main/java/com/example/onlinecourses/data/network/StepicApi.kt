package com.example.onlinecourses.data.network

import com.example.onlinecourses.data.dto.CoursesSearchResponse
import retrofit2.Response
import retrofit2.http.GET

interface StepicApi {
    @GET("/courses")
    suspend fun searchCourses(
    ): Response<CoursesSearchResponse>

}
