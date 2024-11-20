package com.example.onlinecourses.data.dto

data class CoursesSearchResponse(
    val meta: RequestMetadata,
    val courses: List<CourseDto>
) : Response()
