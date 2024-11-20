package com.example.onlinecourses.presentation

import com.example.onlinecourses.domain.models.Course
import com.example.onlinecourses.domain.models.ErrorType

sealed interface SearchState {
    data class Content(val data: List<Course>): SearchState
    data class Error(val type: ErrorType): SearchState
}