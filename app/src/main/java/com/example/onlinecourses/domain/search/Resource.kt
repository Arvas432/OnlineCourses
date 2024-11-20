package com.example.onlinecourses.domain.search

import com.example.onlinecourses.domain.models.Course

sealed class Resource {
    data class Success(val data: List<Course>) : Resource()
    data class Error(val code: Int) : Resource()

}