package com.example.onlinecourses.data.dto

data class CourseDto(
    val id: Long?,
    val cover: String?,
    val summary: String?,
    val price: String?,
    val authors: List<Long>?,
    val title: String?
)
