package com.example.onlinecourses.domain.models

data class Course(
    val id: Long?,
    val cover: String?,
    val summary: String?,
    val price: String?,
    val authors: List<Long>?,
    val title: String?
)
