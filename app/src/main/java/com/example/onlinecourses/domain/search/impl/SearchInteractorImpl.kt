package com.example.onlinecourses.domain.search.impl

import com.example.onlinecourses.domain.search.Resource
import com.example.onlinecourses.domain.search.SearchInteractor
import com.example.onlinecourses.domain.search.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchInteractorImpl(
    private val searchRepository: SearchRepository
): SearchInteractor {
    override suspend fun search(): Flow<Resource> = flow{
        val resource = searchRepository.search()
        emit(resource)
    }.flowOn(Dispatchers.IO)
}