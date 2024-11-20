package com.example.onlinecourses.domain.search

import kotlinx.coroutines.flow.Flow

interface SearchInteractor {
    suspend fun search(): Flow<Resource>
}